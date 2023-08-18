package com.example.kb1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/")
    public String pindex(@RequestParam(defaultValue = "0") String num1,@RequestParam(defaultValue = "0") String num2,@RequestParam(defaultValue = "+") String yon, Model model){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("yon = " + yon);
        int result = 0;
        if (yon.equals("+"))
            result = Integer.parseInt(num1) + Integer.parseInt(num2);
        else if (yon.equals("-"))
            result = Integer.parseInt(num1) - Integer.parseInt(num2);
        else if (yon.equals("*"))
            result = Integer.parseInt(num1) * Integer.parseInt(num2);
        else if (yon.equals("/"))
            result = Integer.parseInt(num1) / Integer.parseInt(num2);

        model.addAttribute("result", result);
        return "index";
    }
    // @RequestParam : 기본값 설정하는 어노테이션.값이 없으면 defualt값으로 초기화하겠다.
}
