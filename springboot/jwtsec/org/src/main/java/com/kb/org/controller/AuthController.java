package com.kb.org.controller;

import com.kb.org.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
//@CrossOrigin
public class AuthController {
    // controller -> service -> jwtUtils
    private final UserService userService; // @Autowired랑 같은 효과

    @PostMapping("join")
    public ResponseEntity<String> join(){
        // 회원가입하고 나서 join 완료 메세지 클라이언트한테 던져주기
        return ResponseEntity.ok("join");
    }

    @PostMapping("login")
    public ResponseEntity<String> login(){
        // 로그인이 되면 JWT 토큰 생성해서 클라이언트한테 던져주기
        return ResponseEntity.ok(userService.createToken());
    }
}
