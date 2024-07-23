package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.serrvices.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    private Logger logger= LoggerFactory.getLogger(RootController.class);
    @Autowired
    UserService userService;

    @ModelAttribute
    public void  addLoggedInUserInformation(Model model, Authentication authentication){
        if(authentication==null){
            return;
        }
        String username= Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("Adding logged in user information to the model");
        logger.info("Logged user is "+ username);
        User user=userService.getUserByEmail(username);
        if(user==null){
            model.addAttribute("loggedInUser",null);
        }else{
        model.addAttribute("loggedInUser",user);
        }
    }
}
