package com.spring.dao;

import java.io.PrintWriter;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.spring.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 아이디 체크
	@Override
	public MemberDto loginCheck(String id) {
		MemberDto res = null;
		res = sqlSession.selectOne("member.selectByloginId", id);
		return res;
	}

	// 회원 가입
	@Override
	public void join(MemberDto dto) {
		sqlSession.insert("member.insertMember", dto);
	}

	// 회원 수정
	@Override
	public void update(MemberDto dto) {
		sqlSession.update("member.updateMember", dto);
	}

	// 회원 탈퇴
	@Override
	public void delete(int idx) {
		sqlSession.delete("member.deleteMember", idx);
	}

	// 아이디 찾기
	@Override
	public String findId(MemberDto dto) {
		String loginId = null;
		loginId = sqlSession.selectOne("member.selectByNameAndEmail", dto);
		return loginId;
	}
	
	// 회원 검색(아이디, 이메일)
		@Override
		public MemberDto userCheck(MemberDto dto) {
			MemberDto res = null;
			res = sqlSession.selectOne("member.selectByLoginIdAndEmail", dto);
			return res;
		}

	// 비밀번호 변경
	@Override
	public void updatePw(MemberDto dto) {
		sqlSession.update("member.updatePw", dto);
	}

}
