package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.controller.GuestBookController;
import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/api")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookController guestBookController;
		
	@Autowired
	GuestDao guestDao;

	// 방명록 메인 화면
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList() {
		System.out.println("api/addList");
		
		return "guestbook/listAjax";
	}

	// 방명록 데이터만 가져오기 ajax
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestVo> list() {
		System.out.println("api/list");			

		// 전체방명록 데이터 가져오기
		List<GuestVo> guestBookList = guestDao.guestSelect();
		System.out.println(guestBookList);
		
		return guestBookList;
	}
	
	
	

}
