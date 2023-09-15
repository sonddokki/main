package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping( "user")
public class UserController {
	
	@RequestMapping( "/loginForm")
	public String loginForm(){
		System.out.println("로그인폼");
		
		return "user/loginForm";
	}

}
