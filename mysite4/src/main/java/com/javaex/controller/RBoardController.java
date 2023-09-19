package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/rbrc")
public class RBoardController {

//	@Autowired
//	private BoardDao boardDao;

	@Autowired
	private RBoardService boardService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(@RequestParam(value="keword", required=false, defaultValue="")String keword, Model model) {
		System.out.println("게시판 리스트");
		List<BoardVo> boardList = boardService.boardList(keword);

		System.out.println(boardList);
		model.addAttribute("bList", boardList);
		return "rboard/list";
	}

	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardRead(@RequestParam("no") int no, @RequestParam("hit") int hit, Model model) {
		System.out.println("게시판 글읽기");

		BoardVo boardVo = boardService.boardRead(no,hit);
		model.addAttribute("boardRead", boardVo);

		return "rboard/read";
	}

	// 게시글 작성 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("게시판 작성 폼");
		return "rboard/writeForm";
	}

	// 게시판 글 등록
	@RequestMapping(value = "/boardInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardInsert(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("게시판에 글등록");		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());		
		if(boardVo.getGroupNo() == 0) {
			System.out.println("글등록실행");
			// 기본글등록실행
			boardService.boardInsert(boardVo);
			return "redirect:list";
		}
		// 댓글등록실행
		System.out.println("댓글등록실행");
		boardService.rboardInsert(boardVo);
		return "redirect:list";
	}

	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("게시판 글 수정폼");
		int hit = 0;
		BoardVo boardVo = boardService.boardRead(no,hit);
		model.addAttribute("boardRead", boardVo);
		return "rboard/modifyForm";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("게시판 글 수정");
		boardService.boardUpdate(boardVo);
		int no = boardVo.getNo();
		return "redirect:read?no=" + no + "&hit=0";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardDelete(@ModelAttribute BoardVo boardVo) {
		System.out.println("게시판 글 삭제");
		boardService.boardDelete(boardVo);
		return "redirect:list";
	}

}
