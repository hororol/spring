package com.spring.service;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.comm.LoginUtil;
import com.spring.comm.Message;
import com.spring.comm.Validate;
import com.spring.dao.MemberDao;
import com.spring.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberDao dao;

	@Autowired
	private Message message;

	@Autowired
	private Validate validate;

	@Autowired
	private LoginUtil loginUtil;

	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	PasswordEncoder bcryptPasswordEncoder;

	// '/login'이란 주소가 들어오면 UserDetailService를 구현한 클래스 내에 loadUserByUsername메소드가 자동으로 실행된다.
	// loadUserByUsername의 매개변수는 화면에서 넘어온 input의 name속성에 설정한 이름과 동일해야한다.
	// session > Authetication > userDetail user
	@Override
	public UserDetails loadUserByUsername(String member_loginId) throws UsernameNotFoundException {
		MemberDto res = dao.loginCheck(member_loginId);
		if(res != null) {
			return new SecurityUser(res);
		}
		else {
			throw new UsernameNotFoundException("사용자가 없습니다.");
		}
	}

	// 아이디 존재 여부 체크
	// 회원가입시 아이디 중복 여부 확인할때 사용
	public boolean ExistId(String loginId) {
		MemberDto res = dao.loginCheck(loginId);
		if (res == null) { // 사용가능한 아이디면 true
			return true;
		} else {
			return false;
		}
	}

	// 회원 가입
	public String join(MemberDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그인 여부 확인
		if (loginUtil.isLogin(request)) {
			message.alert(response, "alert('로그인이 되어있습니다.');");
			return "redirect:/";
		}

		// 유효성 검사와 빈 값 검사
		String logindId = dto.getMember_loginId();
		String password = dto.getMember_password();
		String passwordCheck = dto.getMember_passwordCheck();
		String name = dto.getMember_name();
		String tel = dto.getMember_tel();
		String email = dto.getMember_email();
		String category = dto.getMember_category();

		if (!validate.isValidated(logindId, "^[a-z0-9]{5,19}$")
				| !validate.isValidated(password, "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$")
				| !validate.isValidated(name, "^[가-힣]{2,10}$") | !validate.isValidated(tel, "^[0-9]{10,12}$")
				| !validate.isValidated(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			message.alert(response, "alert('필수 입력란을 작성해 주세요.');history.back();");
		}

		if (!password.equals(passwordCheck)) {
			message.alert(response, "alert('비밀번화와 일치하게 작성해 주세요.');history.back();");
		}

		// 패스워드 암호화
		password = bcryptPasswordEncoder.encode(password);
		dto.setMember_password(password);

		// 회원가입
		dao.join(dto);

		return "redirect:/login";
	}

	// 마이페이지 수정
	public String update(MemberDto dto, Authentication authentication) {
		
		String tel = dto.getMobileNo_1() + dto.getMobileNo_2() + dto.getMobileNo_3();
		dto.setMember_tel(tel);
		// 회원 수정
		dao.update(dto);

		MemberDto res = dao.loginCheck(dto.getMember_loginId());
		SecurityUser scUser = (SecurityUser)authentication.getPrincipal();
		scUser.getMember().setMember_tel(res.getMember_tel());
		scUser.getMember().setMember_email(res.getMember_email());
		
		return "redirect:/member/myPage";
	}

	// 회원 탈퇴
	public String delete(Authentication authentication) {
		SecurityUser scUser = (SecurityUser)authentication.getPrincipal();
		int idx = scUser.getMember().getMember_idx();
		dao.delete(idx);
		return "redirect:/logout";
	}

	// 아이디 찾기
	public String findId(MemberDto dto, Model model, HttpServletResponse response) throws IOException {
		// 입력받은 이름과 이메일
		String name = dto.getMember_name();
		String email = dto.getMember_email();

		// 유효성 검사
		if (validate.isEmpty(name) | validate.isEmpty(email)) {
			message.alert(response, "alert('성명과 이메일을 입력해주세요.');history.back();");
		}

		String loginId = dao.findId(dto);
		
		model.addAttribute("loginId", loginId);
		
		return "/member/findIdResult";
	}

	// 비밀번호 찾기
	public String findPwPro(MemberDto dto, Model model, HttpServletResponse response)
			throws IOException {
		// 입력받은 아이디와 이메일
		String id = dto.getMember_loginId();
		String email = dto.getMember_email();

		// 유효성 검사
		if (validate.isEmpty(id) | validate.isEmpty(email)) {
			message.alert(response, "alert('아이디와 이메일을 입력해주세요.');history.back();");
		}
		
		// 회원 정보 불러오기
		MemberDto res = dao.userCheck(dto);

		// 존재하지 않는 아이디일 경우
		if (res == null) {
			message.alert(response, "alert('존재하지 않는 아이디입니다.');history.back();");
		}
		
		model.addAttribute("res", res);
		
		return "/member/updatePw";
	}

	// 비밀번호 변경
	public String updatePwPro(MemberDto dto, HttpServletResponse response) throws IOException {
		
		System.out.println("여기로 오나");
		String pw = dto.getMember_password();
		String pwcheck = dto.getMember_passwordCheck();

		// 유효성 검사
		if (validate.isEmpty(pw) | validate.isEmpty(pwcheck)) {
			message.alert(response, "alert('비밀번호를 입력해주세요.');history.back();");
		}

		// 일치하는지 확인
		if (!pw.equals(pwcheck)) {
			message.alert(response, "alert('비밀번호와 일치하지 않습니다.');history.back();");
		}
		
		// 비밀번호 암호화
		pw = bcryptPasswordEncoder.encode(pw);
		dto.setMember_password(pw);
		
		// 비밀번호 변경
		dao.updatePw(dto);

		return "redirect:/login";
	}

}
