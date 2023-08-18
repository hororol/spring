package com.kb.org.service;

import com.kb.org.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtils jwtUtils;

    public String createToken(){
        return jwtUtils.createToken("mh", 1000*60*5L); //1000*60L : 1분
    }
}