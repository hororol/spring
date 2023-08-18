package com.example.demo.controller;

import com.example.demo.myclass.AA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.myclass.BB;

@RestController // 주소를 호출하면 응답하는 클래스
public class AController {

    @Autowired // 스프링객체통안에 있는 객체를 가져와서 초기화하겠다.
    AA aa3;

    @Autowired
    AA aa4;

    @Autowired
    BB bb1;

    @Autowired
    BB bb2;


    @GetMapping("aa")
    public String aa(){
        AA aa1 = new AA();
        AA aa2 = new AA();
        System.out.println("aa1 = " + aa1);
        System.out.println("aa2 = " + aa2);

        System.out.println("aa1.a = " + aa1.a);
        System.out.println("aa2.a = " + aa2.a);

        aa1.a = 20;

        System.out.println("aa1.a = " + aa1.a);
        System.out.println("aa2.a = " + aa2.a);

        System.out.println("aa3 = " + aa3.a);
        System.out.println("aa4 = " + aa4.a);

        aa3.a = 40;

        System.out.println("aa3 = " + aa3.a);
        System.out.println("aa4 = " + aa4.a);

        System.out.println("bb1 = " + bb1.b);
        System.out.println("bb2 = " + bb2.b);

        bb1.b = 50;

        System.out.println("bb1 = " + bb1.b);
        System.out.println("bb2 = " + bb2.b);


        return "hfdlkalfsafs";
    }

    @GetMapping("bb")
    public String bb(){
        return "bb";
    }
}
