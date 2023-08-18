package com.example.vs.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.vs.filter.JwtAuthFilter;
import com.example.vs.service.CustomOauth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration // spring 객체 설정하는 클래스입니다.
@EnableWebSecurity // spring security 재정의하는 클래스입니다.
@RequiredArgsConstructor // @autowire 안쓰고 private final 설정하는 것, 객체 주입합니다.
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomOauth2UserService oauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            // 보안 토큰 넘어와야 되는데 그거 안하겠다. 보안토큰으로 DDOS공격을 막는다.
            .csrf(csrf -> csrf.disable())
            // 크롬 브라우저 정책상 다른 요청을 허용하지 않는데 그것을 안하겠다.
            .cors(cors -> cors.configurationSource(req ->{

                var cosconfig = new CorsConfiguration(); // java 17버전
                cosconfig.setAllowedOrigins(List.of("*"));
                cosconfig.setAllowedHeaders(List.of("*"));
                cosconfig.setAllowedMethods(List.of("*"));
                return cosconfig;
            }))
            .authorizeHttpRequests(
                (authorizeRequest) -> authorizeRequest
                // 회원가입 로그인 허용
                .requestMatchers("/auth/**").permitAll()
                // user로 요청하는 것은 허용
                .requestMatchers("/user/**").permitAll()
                // 다른 행동은 인증이 필요하다.
                // 인증이 필요하기 때문에 google 로그인 페이지로 이동한다.
                .anyRequest().authenticated()
            )
            .oauth2Login((oauth2Login) -> oauth2Login
                .userInfoEndpoint((userInfo) -> userInfo
                    .userService(oauth2UserService)
            ))
            // session 방식 안하고 JWT 할 계획임
            .sessionManagement(ss-> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Username 체크하기 전에 jwtAuthFilter 객체 들어가야함
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
}
