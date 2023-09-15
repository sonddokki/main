package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping( "user")
public class UserController {
	
	@RequestMapping( "/loginForm")
	public String loginForm(){
		System.out.println("로그인폼");
		
		return "user/loginForm";
	}
	
	@RequestMapping( "/login")
	public String login(@RequestParam("id") String id,
			            @RequestParam("pw") String pw){
		System.out.println("로그인");
		
		System.out.println(id + " / " + pw);
		
		
		return "";
	}

}
