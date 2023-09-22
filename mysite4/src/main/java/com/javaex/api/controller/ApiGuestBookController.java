package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.controller.GuestBookController;
import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/api")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookController guestBookController;
		
	@Autowired
	GuestbookService GuestbookService;

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
		List<GuestVo> guestBookList = GuestbookService.guestSelect();
		System.out.println(guestBookList);
		
		return guestBookList;
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestVo guestVo) {
		System.out.println("api/add");

		System.out.println("등록할 값 "+ guestVo);
		
		GuestVo gVo = GuestbookService.addGuest(guestVo);
		
		System.out.println("등록된 값 "+ gVo);		
		
		
		return "redirect:addList";
	}
	

}
