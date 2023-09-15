package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserDao userDao;

	@RequestMapping("loginForm")
	public String loginForm() {
		System.out.println("로그인폼");
		return "user/loginForm";
	}

	@RequestMapping("login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("로그인");
		UserVo authUser = userDao.select(userVo);
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}

	// joinForm
	@RequestMapping("joinForm")
	public String joinForm() {
		System.out.println("joinForm");
		return "user/joinForm";
	}

	// joinOk
	@RequestMapping("joinOk")
	public String joinOk(@ModelAttribute UserVo userVo) {		
		System.out.println("joinOk");
		userDao.userInsert(userVo);		
		return "user/joinOk";
	}
	
	

}
