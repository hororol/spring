package com.example.vs.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    // ctrl + space 키 누르면 자동 완성 기능 활성화
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorization = request.getHeader("authorization");
        System.out.println(authorization);

        if(authorization == null || !authorization.startsWith("bearer")){
            System.out.println("auth/login auth/join은 인증모드 없이도 진행 가능");
            System.out.println("왜냐하면 permitAll해 놨기 때문");
            System.out.println("Header 안에 authorization 이 안들어왔네");
            // 그 다음꺼 진행해라
            filterChain.doFilter(request, response);
            return;
        }
        
        System.out.println("여기 온다");

        UserDetails user = User.builder().username("rrr").password("").build();

        // 인증모드 달아라
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null,
                        List.of(new SimpleGrantedAuthority("USER")));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 그 다음꺼 진행해라
        filterChain.doFilter(request, response);
    }
}
