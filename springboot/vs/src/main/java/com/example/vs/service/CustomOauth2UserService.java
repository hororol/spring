package com.example.vs.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
// log 설정하게 되면 콘솔에 출력하지 않고 파일에 출력할 수 있다.
// 파일에 에러내용만 출력하게 되면 실제 서버 운영시
// 그 다음날에 파일을 열어서 에러 내용 확인 가능하다
// 콘솔은 개발시에 사용할 수 있고 파일은 실제 서버 운영시에 출력내용을 적용할 수 있다.
public class CustomOauth2UserService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest,OAuth2User>{
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        log.info("oAuth2User = {}",oAuth2User);
        log.info("userRequest = {}",userRequest);
        log.info("userRequest.getAccessToken() = {}", userRequest.getAccessToken());
        // log.info("userRequest.getClientRegistration() = {}", userRequest.getClientRegistration());
        return null;
    }

    // session 방식
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
