package com.spring.comm;


import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validate {
	// str이 null이거나 빈값이면 true를 반환
	public boolean isEmpty(String str) {
		if (str == null | "".equals(str.trim()) ) {
			return true;
		}
		return false;
	}
	
	// 정규표현식에 맞는지 유효성 검사와 빈 값 검사
	public boolean isValidated(String data, String regex) {
		boolean isValidated = true;
		
		// 유효성 검사를 통과 못하면 false를 반환
		if(data == null | data.equals("") | !Pattern.matches(regex, data)) {
			isValidated = false;
		}
		
		return isValidated;
	}
}
