package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.serrvices.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    Logger logger= LoggerFactory.getLogger(UserController.class);



    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }


    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication){

        String username= Helper.getEmailOfLoggedInUser(authentication);

        logger.info("Logged user is "+ username);
        User user=userService.getUserByEmail(username);
        model.addAttribute("loggedInUser",user);
        return "user/profile";
    }
}
