package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 생성자, 메소드 gs

	// 메소드 일반

	// (1) 게시판 리스트
	public List<BoardVo> boardSelect(String keword) {
		System.out.println("dao");
		List<BoardVo> boardList = sqlSession.selectList("rboard.selectBoard", keword);
		System.out.println(boardList);
		return boardList;
	}

	// (2) 게시판 읽기
	public BoardVo boardRead(int no) {
		return sqlSession.selectOne("rboard.leadBoard", no);
	}

	// (3) 게시판 조회수 올리기
	public void hitUp(int no) {
		sqlSession.update("rboard.hitUp", no);
	}

	// (4-1) 계층형 게시판 글 등록
	public void boardInsert(BoardVo boardVo) {
		sqlSession.insert("rboard.insertBoard", boardVo);
	}

	// (4-2) 계층형 게시판 댓글 등록
	public void rboardInsert(BoardVo boardVo) {
		System.out.println("dao");
		sqlSession.insert("rboard.insertRboard", boardVo);
	}
	
	// (4-3) 계층형 게시판 댓글 등록시 처리
	public void rboardUpdate(BoardVo boardVo) {
		sqlSession.insert("rboard.updateRboard", boardVo);
	}	

	// (5) 게시판 글 수정
	public void boardUpdate(BoardVo boardVo) {
		sqlSession.update("rboard.updateBoard", boardVo);
	}

	// (6) 게시판 삭제
	public void boardDelete(BoardVo boardVo) {
		sqlSession.delete("rboard.deleteBoard2", boardVo);
	}	

	// 보드리스트 페이징 해보기

}
