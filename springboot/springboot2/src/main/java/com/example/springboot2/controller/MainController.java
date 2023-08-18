package com.example.springboot2.controller;

import com.example.springboot2.obj.AA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 주소를 맵핑하는 클래스
public class MainController {
    @Autowired
    AA aa;

    @GetMapping("/")
    public String index() {
        System.out.println(aa);
        return "index";
    }
}
