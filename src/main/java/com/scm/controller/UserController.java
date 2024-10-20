package com.scm.controller;

import com.scm.helper.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
    return "user/dashboard";
    }


    @RequestMapping(value = "/profile")
    public String userProfile(Principal principal){
//        String name = principal.getName();
        String name = Helper.getEmailOfLoggedInUser(principal);

        logger.info("user profile:{}",name);

        return "user/profile";

    }
}

