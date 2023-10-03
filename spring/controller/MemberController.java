package com.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.MemberDto;
import com.spring.service.MemberService;
import com.spring.service.SecurityUser;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	@Qualifier("memberService")
	MemberService service;

	@GetMapping("test")
	@ResponseBody
	public String test(Authentication authentication){
		// principal은 형변환을 해줘야 나온다.
		System.out.println((SecurityUser)authentication.getPrincipal());
		return "";
	}

	// 회원가입 페이지로 이동
	@RequestMapping(value = "joinPage")
	public String join() {
		return "member/joinForm";
	}

	// 회원가입
	@PostMapping(value = "join")
	public String join(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 페이지로 이동
		return service.join(dto, request, response);
	}

	// 아이디 존재 여부 체크
	// 회원가입시 아이디 중복 여부 확인할때 사용
	@GetMapping("checkId")
	public @ResponseBody String checkId(@RequestParam String loginId) {
		boolean exist = service.ExistId(loginId);
		if (exist) { // 사용가능한 아이디면
			return "success";
		} else {
			return "false";
		}
	}

	// 마이페이지로 이동
	// 로그인후 사용가능
	@GetMapping("myPage")
//	@PreAuthorize("isAuthenticated()") -> 로그인이 되지않은 상태에서는 접속은 되지만 sec태그로 설정해 놓은것은 보이지 않는다.
	public String myPage() {
		return "member/myPage";
	}

	// 마이페이지 수정
	// 로그인후 사용가능
	@PostMapping("/auth/update")
//	@PreAuthorize("isAuthenticated()")
	public String update(MemberDto dto, Authentication authentication) {
		return service.update(dto, authentication);
	}

	// 회원 탈퇴
	// 로그인후 사용가능
	@GetMapping("/auth/delete")
//	@PreAuthorize("isAuthenticated()")
	public String delete(Authentication authentication) {
		return service.delete(authentication);
	}

	// 아이디 찾는 페이지로 이동
	@GetMapping(value = "findId")
	public String findId() {
		return "member/findId";
	}

	// 아이디 찾기
	@PostMapping("findIdPro")
	public String findIdPro(MemberDto dto, Model model, HttpServletResponse response) throws IOException {
		return service.findId(dto, model, response);
	}

	// 비밀번호 찾는 페이지로 이동
	@GetMapping(value = "findPw")
	public String findPw() {
		return "member/findPw";
	}

	// 비밀번호 찾기
	@PostMapping("findPwPro")
	public String findPwPro(MemberDto dto, Model model, HttpServletResponse response) throws IOException {
		return service.findPwPro(dto, model, response);
	}

	// 비밀번호 변경하는 페이지로 이동
	@PostMapping(value = "updatePw")
	public String updatePw(MemberDto dto) throws IOException {
		return "member/updatePw";
	}

	// 비밀번호 변경
	@PostMapping(value = "updatePwPro")
	public String updatePwPro(MemberDto dto, HttpServletResponse response) throws IOException {
		return service.updatePwPro(dto, response);
	}

}

// @RequestBody : post 방식
// @RequestParam : get 방식
