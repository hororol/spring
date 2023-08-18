package com.jpa.jpa.conf;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/Member/**", "/item/**", "/images/**").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
//                        .requestMatchers("/FreeBoard/**").permitAll()
                        .anyRequest().authenticated() // 다른 페이지를 요청하였을때 무조건 인증을 해라
                )
                .formLogin(
                        (form) -> form
                                .loginPage("/account/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/account/login?error")
                                .permitAll()
                ) // security 자체에서 만든 로그인 폼을 사용하지 않고 내가 만든 로그인 폼을 사용하겠다.
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

}
