package com.example.vs.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vs.VsApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController // html 파일 찾지 않고 문자열 보내기
@RequestMapping("auth") // 경로 auth로 시작하면 여기서 작동
public class AuthController {
    
    @PostMapping("join")
    public String join(){
        return "join success";
    }

    @PostMapping("login")
    public String login(){
        Claims claims = Jwts.claims();
        claims.put("username", "rrr");

        String mytoken = Jwts.builder()
                        // username 도 추가 해서 암호화 해줘
                        .setClaims(claims)
                        // 현재 시간으로 발행되었다.
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        // 5분뒤에 사용할수 없다.
                        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*5l))
                        // KEY 값으로 암호화 해라
                        .signWith(SignatureAlgorithm.HS512, VsApplication.key)
                        .compact();
        
        return mytoken;
    }

}
