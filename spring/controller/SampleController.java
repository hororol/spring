package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleController {

	
	@GetMapping("all")
	public String all() {
		return "all permit";
	}
	
	@GetMapping("member")
	public String member() {
		return "member permit";
	}
	
	@GetMapping("admin")
	public String admin() {
		return "admin permit";
	}
}
