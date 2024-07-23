package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.serrvices.ContactService;
import com.scm.serrvices.ImageService;
import com.scm.serrvices.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user/contacts")
public class ContactController {


    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;



    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addContactView(Model model){
        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String saveContactView(@Valid @ModelAttribute("contactForm") ContactForm contactForm,
                                  BindingResult result,
                                  Authentication authentication,
                                  HttpSession session
                                  ){
        if(result.hasErrors()){
            session.setAttribute("message", Message.builder().content("Please Correct the following errors").type(MessageType.red).build());

            return "user/add_contact";
        }
        String fileName= UUID.randomUUID().toString();

        String username= Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

//        image process.......>>>>>>>

         String fileURL= imageService.uploadImage(contactForm.getContactImage(),fileName);

        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setFavourite(contactForm.isFavourite());
        contact.setUser(user);
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setPicture(fileURL);
        contact.setPublicImageId(fileName);


      contactService.save(contact);
      session.setAttribute("message", Message.builder().content("You have been successfully added a new contact").type(MessageType.green).build());
        return "redirect:/user/contacts/add";
    }


    @RequestMapping()
    public String viewContacts(Model model,
                               Authentication authentication,
                               @RequestParam(value = "page",defaultValue = "0") int page,
                               @RequestParam(value = "size",defaultValue = "10") int size,
                               @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
                               @RequestParam(value = "direction",defaultValue = "asc") String direction
    ){
        String username=Helper.getEmailOfLoggedInUser(authentication);
        User user =userService.getUserByEmail(username);
        Page<Contact> pageContact=contactService.getByUser(user,page,size,sortBy,direction);
        model.addAttribute("pageContact",pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);


        return "user/contacts";
    }

//    public String searchViewHandler(
//            @RequestParam("field") String field,
//            @RequestParam("keyword") String value
//    ){
//
//        return "user/search";
//    }
}
