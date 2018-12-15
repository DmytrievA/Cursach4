package com.DTP.dailyTimePlaner.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Map<String,Object> model,final Principal principal) {
        if(principal == null)
        {
            model.put("btnName","SignIn");
            model.put("action","/login");
            model.put("method","get");
        }
        else
        {
            model.put("btnName","SignOut");
            model.put("action","/logout");
            model.put("method","post");
        }
        return "main";
    }
}
