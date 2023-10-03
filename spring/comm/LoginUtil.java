package com.spring.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.spring.dto.MemberDto;

@Component
public class LoginUtil {
	// 로그인 여부
	public boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("res");
		if(dto != null) { // 로그인이 되어있다면
			return true;
		}else {
			return false;
		}
	}

}
