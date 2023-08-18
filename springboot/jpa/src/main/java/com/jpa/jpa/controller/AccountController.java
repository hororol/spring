package com.jpa.jpa.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

    @GetMapping("login")
    public String login(){
        return "account/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
