package com.example.vs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 인증이 있어야 되는 요청
@RestController
@RequestMapping("freeboard")
public class freeboardController {
    
    @GetMapping("list")
    public String list(){
        return "list";
    }
}
