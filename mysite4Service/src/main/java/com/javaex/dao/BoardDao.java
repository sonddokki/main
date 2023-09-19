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
	public List<BoardVo> boardSelect() {
		System.out.println("dao");
		List<BoardVo> boardList = sqlSession.selectList("rboard.selectBoard");
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
		sqlSession.update("board.updateBoard", boardVo);
	}

	// (6) 게시판 삭제
	public void boardDelete(BoardVo boardVo) {
		sqlSession.delete("board.deleteBoard", boardVo);
	}

	// (7)게시판 검색
	public List<BoardVo> boardSearch(String search) {
		List<BoardVo> boardList = sqlSession.selectList("board.searchBoard", search);
		return boardList;
	}

	// 보드리스트 페이징 해보기

	/*
	 * 리스트 메소드와 검색 메소드 합쳐보기 ( xml 쿼리문에 if문 넣는 문법 찾아보기 )
	 * 
	 * String query = ""; query += " select  bo.no, "; query +=
	 * "         bo.title, "; query += "         bo.content, "; query +=
	 * "         bo.hit, "; query += "         bo.reg_date, "; query +=
	 * "         us.name, "; query += "         us.no "; query +=
	 * " FROM board bo, users us "; query += " where bo.user_no = us.no ";
	 * 
	 * if (!keyword.equals("")) { // keyword가 ""가 아니면 ==> keyword가 있으면 검색 query +=
	 * " and bo.no = ? "; }
	 * 
	 * query += " ORDER BY bo.no desc ";
	 * 
	 * if (!keyword.equals("")) { // keyword가 ""가 아니면 ==> keyword가 있으면 검색 int key =
	 * Integer.parseInt(keyword); pstmt.setInt(1, key); } }
	 * 
	 */

}
