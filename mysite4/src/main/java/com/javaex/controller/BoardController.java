package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardDao;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/brc")
public class BoardController {

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private BoardService boardService;

	// (검색X, 페이징X)
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String List(Model model) {
		System.out.println("게시판 리스트");
		List<BoardVo> bList = boardService.boardSelect();
		model.addAttribute("bList", bList);
		return "board/list";
	}

	// (검색O, 페이징X)
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardSearch(@RequestParam("search") String search, Model model) {
		System.out.println("게시판 글 검색");
		List<BoardVo> sList = boardDao.boardSearch(search);
		model.addAttribute("bList", sList);
		return "board/list";
	}

	// (검색X, 페이징O)
	@RequestMapping(value = "/list3", method = { RequestMethod.GET, RequestMethod.POST })
	public String pagingList(@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage ,Model model) {
		System.out.println("페이징 게시판 리스트 0927");
		System.out.println(crtPage);
	
		// service를 통해서 리스트를 가져온다
		Map<String, Object> pMap = boardService.getBoardList3(crtPage);
		System.out.println(pMap);
		
		model.addAttribute("pMap", pMap);
		
		return "board/list3";
	}
	
	// (검색X, 페이징O)
		@RequestMapping(value = "/list4", method = { RequestMethod.GET, RequestMethod.POST })
		public String list4(@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage
						   ,@RequestParam(value="search", required = false, defaultValue = "") String search 
						   ,Model model) {
			System.out.println("페이징 게시판 리스트 list4");
			System.out.println(crtPage);
			System.out.println(search);
		
			// service를 통해서 리스트를 가져온다
			Map<String, Object> pMap = boardService.getBoardList4(crtPage, search);
			System.out.println(pMap);
			
			model.addAttribute("pMap", pMap);
			
			return "board/list4";
		}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("게시판 작성폼");
		return "board/writeForm";
	}

	@RequestMapping(value = "/boardInsert", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardInsert(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("게시판 등록");

		// 임시
		for (int i = 1; i <= 157; i++) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			boardVo.setUserNo(authUser.getNo());
			boardVo.setTitle(i + " 번째 글 제목");
			boardVo.setContent(i + " 번째 글 내용");

			boardDao.boardInsert(boardVo);

		}

		return "redirect:list";

	}

	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardRead(@RequestParam("no") int no, @RequestParam("hit") int hit, Model model) {
		System.out.println("게시판 글읽기");
		// 파라미터 hit에 1이 들어있을때만 조회수 상승, 수정이나 수정후 게시글창에서 조회수가 상승하지 않기 위함
		if (hit == 1) {
			boardDao.hitUp(no);
			System.out.println("조회수 1 상승");
		}
		BoardVo boardVo = boardDao.boardRead(no);
		model.addAttribute("boardRead", boardVo);
		return "board/read";
	}

	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("게시판 글 수정폼");
		BoardVo boardVo = boardDao.boardRead(no);
		model.addAttribute("boardRead", boardVo);
		return "board/modifyForm";
	}

	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("게시판 글 수정");
		boardDao.boardUpdate(boardVo);
		int no = boardVo.getNo();
		return "redirect:read?no=" + no + "&hit=0";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String boardDelete(@ModelAttribute BoardVo boardVo) {
		System.out.println("게시판 글 삭제");
		boardDao.boardDelete(boardVo);
		return "redirect:list";
	}

}
