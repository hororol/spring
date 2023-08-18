package com.example.DBPractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

//    // 뷰의 요청 경로 지정, 내가 목표로 하는 view의 경로
//    @RequestMapping(value = "/create", method = RequestMethod.GET) // create메소드는 브라우저에서 /create주소가 get방식으로 입력되었을때 book/create 경로의 뷰를 보여준다.
//    public ModelAndView create(){
//        return new ModelAndView("book/create");
//    }
}
