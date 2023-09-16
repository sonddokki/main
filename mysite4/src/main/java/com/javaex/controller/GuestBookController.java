package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/gbc")
public class GuestBookController {
	
	@Autowired
	GuestDao guestDao;
	
	
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model){
		System.out.println("방명록리스트");
		List<GuestVo> guestList = guestDao.guestSelect();
		System.out.println(guestList);
		model.addAttribute("gList", guestList);		
		return "guestbook/addList";
	}	
	
	// 삭제폼
	
	// 삭제
	
	
	

}
