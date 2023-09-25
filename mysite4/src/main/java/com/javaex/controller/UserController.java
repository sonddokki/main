package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.JsonResultVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("로그인폼");
		return "user/loginForm";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("로그인");
		UserVo authUser = userService.select(userVo);
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println(authUser);
			return "redirect:/main";
		} else {
			return "redirect:/user/loginForm?result=fail";
		}
	}

	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("회원가입폼");
		return "user/joinForm";
	}

	@RequestMapping(value = "/joinOk", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk(@ModelAttribute UserVo userVo) {
		System.out.println("회원가입성공");

		System.out.println("가입할 정보 " + userVo);
		userService.userInsert(userVo);
		return "user/joinOk";
	}	

	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("회원수정폼");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo modifyUser = userService.userUpdateSelect(authUser);
		model.addAttribute("modifyUser", modifyUser);
		return "user/modifyForm";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("회원수정");
		userService.update(userVo);
		// 회원정보 업데이트후 세션정보도 업데이트
		UserVo authUser = userService.select(userVo);
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("로그아웃");
		session.invalidate();
		return "redirect:/main";
	}

	@ResponseBody // 반환값을 html의 body로 보냄
	@RequestMapping(value = "/idcheck", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResultVo idcheck(@RequestParam(value = "id") String id) {
		System.out.println("중복체크컨트롤러");
		// true 사용가능 , false 사용불가
		boolean check = userService.idCheck(id);
		System.out.println(check);

		JsonResultVo jsonResultVo = new JsonResultVo();
		jsonResultVo.success(check);

		System.out.println(jsonResultVo);

		return jsonResultVo;
	}

}
