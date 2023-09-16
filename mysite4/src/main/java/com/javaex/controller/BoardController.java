package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping("/brc")
public class BoardController {
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model){
		System.out.println("게시판 리스트");
		List<BoardVo> bList = boardDao.boardSelect();
		System.out.println(bList);
		model.addAttribute("bList", bList);		
		return "board/list";
	}	
	
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(){
		System.out.println("게시판 작성폼");		
		return "board/writeForm";
	}	
	
	@RequestMapping(value = "/boardInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardInsert(@ModelAttribute BoardVo boardVo, HttpSession session){
		System.out.println("게시판 등록");
		UserVo authUser = (UserVo)session.getAttribute("authUser");	
		boardVo.setUserNo(authUser.getNo());
		boardDao.boardInsert(boardVo);		
		return "redirect:list";
	}	
	
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardRead(){
		System.out.println("게시판 글읽기");		
		return "board/read";
	}	
	
	
	
}
