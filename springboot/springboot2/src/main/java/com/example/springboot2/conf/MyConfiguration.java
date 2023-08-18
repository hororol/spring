package com.example.springboot2.conf;

import com.example.springboot2.obj.AA;
import com.example.springboot2.obj.BB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 개발 환경 설정
public class MyConfiguration {
    @Bean // 스프링객체통안에 이 객체 넣겠다.
    public AA aa(){
       return new AA("존");
    }

    @Bean
    public BB bb(){
        return new BB();
    }
}
