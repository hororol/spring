package com.spring.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.spring.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@ToString
public class SecurityUser extends User {

    private MemberDto member;

    public SecurityUser(MemberDto member) {
        super(member.getMember_name(), member.getMember_password(), AuthorityUtils.createAuthorityList(member.getMember_role()));

        log.info("SecurityUser member.username = {}", member.getMember_name());
        log.info("SecurityUser member.password = {}", member.getMember_password());
        log.info("SecurityUser member.role = {}", member.getMember_role());

        this.member = member;
    }

}
