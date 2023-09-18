package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/brc2")
public class BoardController2 {
	
//	@Autowired
//	private BoardDao boardDao;

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("게시판 리스트");		
		List<BoardVo> boardList =  boardService.getBoardList();
		model.addAttribute("bList", boardList);
		return "board/list";
	}

//	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
//	public String writeForm() {
//		System.out.println("게시판 작성폼");
//		return "board/writeForm";
//	}
//
//	@RequestMapping(value = "/boardInsert", method = { RequestMethod.GET, RequestMethod.POST })
//	public String boardInsert(@ModelAttribute BoardVo boardVo, HttpSession session) {
//		System.out.println("게시판 등록");
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		boardVo.setUserNo(authUser.getNo());
//		boardDao.boardInsert(boardVo);
//		return "redirect:list";
//	}
//
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardRead(@RequestParam("no") int no, @RequestParam("hit") int hit, Model model) {
		System.out.println("게시판 글읽기");
		
		BoardVo boardVo = boardService.boardRead(no);		
		model.addAttribute("boardRead", boardVo);
		
		return "board/read";
	}

//	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
//	public String modifyForm(@RequestParam("no") int no, Model model) {
//		System.out.println("게시판 글 수정폼");
//		BoardVo boardVo = boardDao.boardRead(no);
//		model.addAttribute("boardRead", boardVo);
//		return "board/modifyForm";
//	}
//
//	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
//	public String modify(@ModelAttribute BoardVo boardVo) {
//		System.out.println("게시판 글 수정");
//		boardDao.boardUpdate(boardVo);
//		int no = boardVo.getNo();
//		return "redirect:read?no=" + no + "&hit=0";
//	}
//
//	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
//	public String boardDelete(@ModelAttribute BoardVo boardVo) {
//		System.out.println("게시판 글 삭제");
//		boardDao.boardDelete(boardVo);
//		return "redirect:list";
//	}
//
//	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
//	public String boardSearch(@RequestParam("search") String search, Model model) {
//		System.out.println("게시판 글 검색");
//		List<BoardVo> sList = boardDao.boardSearch(search);
//		model.addAttribute("bList", sList);
//		return "board/list";
//	}

}
