package com.jpa.jpa.controller;

import com.jpa.jpa.entity.Member;
import com.jpa.jpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Member")
public class MemberController {
    @Autowired
    MemberService memberService;

    // 회원가입 화면
    @GetMapping("SignUp")
    public String signup(){
        return "member/signup";
    }

    // 화원가입 해서 테이블에다가 저장을 해야 합니다.
    @PostMapping("SignUp")
    public String psignup(Member member){
        memberService.save(member);
        return "redirect:/";
    }
}