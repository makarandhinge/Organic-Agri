package com.scm.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {
public static void messageRemove(){

    try{
        System.out.println("Removeing message from session");
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");
    }catch(Exception e){
        System.out.println("Error in SessionHelper"  + e);
        e.printStackTrace();
    }
}}
