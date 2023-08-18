package com.jpa.jpa.controller;

import com.jpa.jpa.entity.FreeBoard;
import com.jpa.jpa.entity.Role;
import com.jpa.jpa.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import com.jpa.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String index(
//            @Autowired Authentication authentication // 로그인이 없으면 오류를 띄움
    ){
//        System.out.println("로그인 유무=" + authentication.isAuthenticated());
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getDetails());

//        FreeBoard f1 = new FreeBoard().builder() // FreeBoard를 생성
//                .name("홍길동")
//                .content("잠와요")
//                .build();
//        freeBoardRepository.save(f1); // f1 저장후 알아서 sql구문 생성
//        List<FreeBoard> list = freeBoardRepository.findAll(); // save한 것을 모두 불러온다.
//        System.out.println(list);

        roleRepository.save(new Role(1,"User"));
        roleRepository.save(new Role(2,"Admin"));
        roleRepository.save(new Role(3,"Manager"));
        return "index";
    }
}
