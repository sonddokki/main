package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;


@Controller
@RequestMapping("/brc")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model){
		System.out.println("게시판리스트");
		List<BoardVo> bList = boardDao.boardSelect();
		System.out.println(bList);
		model.addAttribute("bList", bList);		
		return "board/list";
	}	

}
