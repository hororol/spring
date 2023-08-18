package com.kb.org.conf;

import com.kb.org.conf.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter; // 객체 주입

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // DDos공격 막는 코드
                .authorizeHttpRequests((authorizeRequest) ->
                        authorizeRequest
                                // 회원가입 로그인 허용
                                .requestMatchers("/auth/**").permitAll()
                                // 다른 것들은 인증이 필요하다.
                                .anyRequest().authenticated()
                )
                .sessionManagement((sessionManagement) ->
                        // 세션은 허용하지 않겠다.
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) /* session을 사용하지 않음 */
                )
                // 인증방식은 provider에서 제공하는 방식으로 하겠다
                //.authenticationProvider(authenticationProvider)
                // Aop Jsp tomcat web.xml 필터
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        //.httpBasic(withDefaults()); /* jwt 사용으로 사용하지 않음 */

        return http.build();
    }
}