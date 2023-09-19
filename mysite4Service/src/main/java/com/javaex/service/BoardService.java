package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getBoardList(String keword) {
		System.out.println("@Service");
		List<BoardVo> boardList = boardDao.boardSelect(keword);
		return boardList;
	}

	// 게시판 읽기 (조회수 상승)
	public BoardVo boardRead(int no) {
		boardDao.hitUp(no);
		return boardDao.boardRead(no);
	}

	// 게시판 글 등록
	public void boardInsert(BoardVo boardVo) {
		boardDao.boardInsert(boardVo);
	}	

	// 게시판 댓글 등록	
	public void rboardInsert(BoardVo boardVo) {
		System.out.println("서비스에서 등록시 "+boardVo);
		boardDao.rboardUpdate(boardVo);
		boardDao.rboardInsert(boardVo);
	}
	
	// 게시판 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("서비스에서 등록시 "+boardVo);
		boardDao.boardUpdate(boardVo);
	}
	
	// 게시판 삭제
	public void boardDelete(BoardVo boardVo) {
		System.out.println("삭제 실행 "+boardVo);
		boardDao.boardDelete(boardVo);
	}
	
}
