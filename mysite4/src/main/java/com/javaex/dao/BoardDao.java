package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// (1-1) 게시판 리스트
	public List<BoardVo> boardSelect() {
		System.out.println("dao");
		List<BoardVo> boardList = sqlSession.selectList("board.selectBoard");
		return boardList;
	}

	// (1-2) 게시판 검색
	public List<BoardVo> boardSearch(String search) {
		List<BoardVo> boardList = sqlSession.selectList("board.searchBoard", search);
		return boardList;
	}

	// (1-3) 페이징 게시판 리스트
	public List<BoardVo> boardSelectList3(int stratRNum, int endRNum) {
		System.out.println("dao 333");

		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("stratRNum", stratRNum);
		pageMap.put("endRNum", endRNum);
		System.out.println(pageMap);

		List<BoardVo> boardList = sqlSession.selectList("board.selectList3", pageMap);
		return boardList;
	}

	// 글 전체 갯수
	public int selectTotalCnt() {
		System.out.println("selectTotalCnt");
		int totalCount = sqlSession.selectOne("board.selectTotalCnt");
		return totalCount;
	}

	// (1-4) 페이징+검색 게시판 리스트
	public List<BoardVo> boardSelectList4(int stratRNum, int endRNum, String search) {
		System.out.println("dao 444");

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
		pageMap.put("stratRNum", stratRNum);
		pageMap.put("endRNum", endRNum);
		System.out.println(pageMap);

		List<BoardVo> boardList = sqlSession.selectList("board.selectList4", pageMap);
		return boardList;
	}

	public int selectTotalCnt(String search) {
		System.out.println("selectTotalCnt");
		int totalCount = sqlSession.selectOne("board.selectTotalCnt2", search);
		System.out.println("Dao " + totalCount);
		return totalCount;
	}

	////////////////////////////////////////////////////////////////

	// (2) 게시판 등록
	public void boardInsert(BoardVo boardVo) {
		sqlSession.insert("board.insertBoard", boardVo);
	}

	// (3) 게시판 읽기
	public BoardVo boardRead(int no) {
		return sqlSession.selectOne("board.leadBoard", no);
	}

	// (4) 게시판 조회수 올리기
	public void hitUp(int no) {
		sqlSession.update("board.hitUp", no);
	}

	// (5) 게시판 글 수정
	public void boardUpdate(BoardVo boardVo) {
		sqlSession.update("board.updateBoard", boardVo);
	}

	// (6) 게시판 삭제
	public void boardDelete(BoardVo boardVo) {
		sqlSession.delete("board.deleteBoard", boardVo);
	}

}
