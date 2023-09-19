package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.BoardVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao boardDao;

	public List<BoardVo> boardList(String keword) {
		System.out.println("@Service");
		List<BoardVo> boardList = boardDao.boardSelect(keword);
		return boardList;
	}

	// 게시판 읽기 (조회수 상승)
	public BoardVo boardRead(int no, int hit) {
		if (hit == 1) {
		boardDao.hitUp(no);
		}
		return boardDao.boardRead(no);
	}

	// 게시판 글 등록
	public void boardInsert(BoardVo boardVo) {
		boardDao.boardInsert(boardVo);
	}	

	// 게시판 댓글 등록	
	public void rboardInsert(BoardVo boardVo) {
		boardDao.rboardUpdate(boardVo);
		boardDao.rboardInsert(boardVo);
	}
	
	// 게시판 수정
	public void boardUpdate(BoardVo boardVo) {
		boardDao.boardUpdate(boardVo);
	}
	
	// 게시판 삭제
	public void boardDelete(BoardVo boardVo) {
		boardDao.boardDelete(boardVo);
	}
	
}
