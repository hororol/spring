package com.kb.org.conf.filter;

import com.kb.org.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

@Component // 객체담는 통에 객체 주입
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("일로오나");

        /*
           로그인 되어 있는지 확인 JWT가 왔는지 확인
           모든 인증 없음 모든 유저 허용 username = rrr로 권한은 user로
        */
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authorization);

        // jwt 토근을 안 가지고 왔으면
        if(authorization == null || !authorization.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        // jwt 토근을 가지고 왔으면
        String token = authorization.split(" ")[1];
        System.out.println(token);

        try {
            if(jwtUtils.isExpired(token)){
                System.out.println("이미 기간 지남");
            }
        }catch (Exception e){
            response.sendRedirect("/auth/login");
//            filterChain.doFilter(request,response);
            return;
        }

        // DB에 가서 username 찾아 가지고 userDetail 만들어야함.
        UserDetails user = User.builder().username("rrr").password("").build();

        // 인증해라
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null,
                        List.of(new SimpleGrantedAuthority("USER")));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 코드 진행
        filterChain.doFilter(request, response);
    }
}

//JWT 발행 = 로그인을 하겠다.
// JWT 발행 두가지 요청은 무조건 허용
// auth/login -> dbselect
// auth/join

// JWT 확인하는 상황
// -> 다른 url 호출할때 다른 요청은 무조건 JWT 확인해야됨