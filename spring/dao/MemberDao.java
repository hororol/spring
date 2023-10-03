package com.spring.dao;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.dto.MemberDto;

public interface MemberDao {

	// selectByLoginId
	public MemberDto loginCheck(String id);

	// 회원 가입
	public void join(MemberDto dto);

	// 회원 수정
	public void update(MemberDto dto);

	// 회원 탈퇴
	public void delete(int idx);

	// 아이디 찾기
	public String findId(MemberDto dto);

	// selectByLoginIdAndEmail
	public MemberDto userCheck(MemberDto dto);

	// 비밀번호 변경
	public void updatePw(MemberDto dto);

}
