package com.kb.org.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    // 이게 토큰 비번 입니다.
    private String key = "mykey.mykey.mykey";


    // 사용자명을 가지고 jwt 토큰 생성
    public String createToken(String username, Long expired){
        // hashmap -> 키 value
        Claims claims = Jwts.claims();
        claims.put("username", username);

        // json 토큰 생성
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expired))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // 토큰 발행한지 시간체크하는 함수
    public boolean isExpired(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
