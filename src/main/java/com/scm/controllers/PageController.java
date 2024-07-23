package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.serrvices.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;

@Controller
public class PageController {
    @Autowired
    UserService userService;

    @GetMapping({"/","/home"})
    public String home(){
        return "home";
    }
    @GetMapping({"/about"})
    public String about(){
        return "about";
    }
    @GetMapping({"/contact"})
    public String contact(){
        return "contact";
    }
    @GetMapping({"/service"})
    public String service(){
        return "service";
    }
    @GetMapping({"/login"})
    public String login(){
        return "login";
    }

    @GetMapping({"/register"})
    public String register(Model model){
        UserForm userForm=new UserForm();
        model.addAttribute("user",userForm);

        return "register";
    }


    @RequestMapping(value="/do-register",method= RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute("user") UserForm userForm, BindingResult bindingResult, HttpSession session){

        if(bindingResult.hasErrors()){
            return "register";
        }
        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://flowbite.com/docs/images/logo.svg");

        User savedUser=userService.saveUser(user);
        System.out.println("user saved");

        Message message=Message.builder().content("Registration Successfull!").type(MessageType.green).build();
        session.setAttribute("message",message);
        return "redirect:/register";
    }

}
