package com.jpa.jpa.service;

import com.jpa.jpa.entity.Member;
import com.jpa.jpa.entity.Role;
import com.jpa.jpa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("나와라 아이디!" + username);
        Member member = memberRepository.findByUsername(username);
        System.out.println(member);
        if(member == null){
            throw new UsernameNotFoundException("그런 회원 없음");
        }
        return User.builder()
                .username(username)
                .password(member.getPassword())
                .roles(new String[]{"USER",})
                .build();
    }

    public void save(Member member) {
        // 회원 중복 확인
        boolean result = validate(member);
     if(result){
                // 패스워드는 암화화해서 저장
                member.setPassword(passwordEncoder.encode(member.getPassword()));
                // 롤을 적용한다.
                List<Role> list = Arrays.asList(new Role(1, "User"), new Role(2, "Admin"));
                member.setRoles(list);
                memberRepository.save(member);
            }
    }

    public boolean validate(Member member){
        Member dbmember = memberRepository.findByUsername(member.getUsername());
        if(dbmember == null){
            return true;
        }else{
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
