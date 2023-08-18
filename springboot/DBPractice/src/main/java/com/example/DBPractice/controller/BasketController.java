package com.example.DBPractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    @PostMapping("/view")
    public String view(){
        return "basket/basket";
    }

}
