package com.example.vs.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("user")
    public String user(Authentication authenticateAction){
        System.out.println("test");

        System.out.println(authenticateAction.getPrincipal());
        return authenticateAction.getName();
    }
    
}
