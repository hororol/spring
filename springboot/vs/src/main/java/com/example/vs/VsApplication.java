package com.example.vs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VsApplication {
	public static String key = "aaa.bbb.ccc";
	public static void main(String[] args) {
		SpringApplication.run(VsApplication.class, args);
	}

}
