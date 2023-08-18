package com.example.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController는 문자 그대로 나가는 것
@Controller // html파일을 찾는것
public class MainHtmlController {
    @GetMapping("aa")
    public String aa(Model model, String num1, String num2){
        System.out.println("출력");
        System.out.println(num1);
        System.out.println(num2);
        if (num1 == null){ // num1이 null이면 0으로 초기화
            num1 = "0";
        }
        if (num2 == null){ // num2이 null이면 0으로 초기화
            num2 = "0";
        }
        model.addAttribute("data", "mydata"); // 키값 : data, value값 : mydata
        model.addAttribute("result", Integer.parseInt(num1)+Integer.parseInt(num2));
        return "aa"; // html파일명을 반환
    }

    @GetMapping("calculator")
    public String calculator(Model model, String num1, String operator, String num2){
        if (num1 == null){
            num1 = "0";
        }
        if (num2 == null){
            num2 = "0";
        }
        if (operator == null){
            operator = "+";
        }
        if(operator.equals("+")) {
            model.addAttribute("result", Integer.parseInt(num1) + Integer.parseInt(num2));
        }else if(operator.equals("-")){
            model.addAttribute("result", Integer.parseInt(num1) - Integer.parseInt(num2));
        }else if(operator.equals("*")){
            model.addAttribute("result", Integer.parseInt(num1) * Integer.parseInt(num2));
        }else {
            model.addAttribute("result", Integer.parseInt(num1) / Integer.parseInt(num2));
        }
        return "calculator";
    }
}
