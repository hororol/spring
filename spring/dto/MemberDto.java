package com.spring.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
	private int member_idx;
	
	private String member_loginId;
	
	private String member_password;
	
	private String member_passwordCheck;
	
	private String member_name;
	
	private String member_tel;

	private String member_email;
	
	private String member_category;

	private LocalDate member_joinDate;
	
	private String member_role;
	
	private LocalDate member_withdrawDate;
	
	private String mobileNo_1;
	
	private String mobileNo_2;
	
	private String mobileNo_3;
}