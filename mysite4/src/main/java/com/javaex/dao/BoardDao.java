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
		List<BoardVo> boardList = sqlSession.selectList("board.selectBoard");
		return boardList;
	}
	
	
	
	/*
	public List<BoardVo> boardSelect() {

		List<BoardVo> boardList;

			String query = "";
			query += " select  bo.no, ";
			query += "         bo.title, ";
			query += "         bo.content, ";
			query += "         bo.hit, ";
			query += "         bo.reg_date, ";
			query += "         us.name, ";
			query += "         us.no ";
			query += " FROM board bo, users us ";
			query += " where bo.user_no = us.no ";
			
			if (!keyword.equals("")) { // keyword가 ""가 아니면 ==> keyword가 있으면 검색
				query += " and bo.no = ? ";
			}
			
			query += " ORDER BY bo.no desc ";

			pstmt = conn.prepareStatement(query);

			// 바인딩**********************************************************************
			if (!keyword.equals("")) { // keyword가 ""가 아니면 ==> keyword가 있으면 검색
				int key = Integer.parseInt(keyword);
				pstmt.setInt(1, key);
			}

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				String name = rs.getString(6);
				int userNo = rs.getInt(7);

				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, name, userNo);

				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardList;

	}

	// 게시판 읽기
	public BoardVo boardRead(int readNo) {

		BoardVo boardVo = new BoardVo();
		System.out.println(readNo);

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select  bo.no, ";
			query += "         bo.title, ";
			query += "         bo.content, ";
			query += "         bo.hit, ";
			query += "         bo.reg_date, ";
			query += "         us.name, ";
			query += "         us.no ";
			query += " FROM board bo, users us ";
			query += " where bo.user_no = us.no ";
			query += " and bo.no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, readNo);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			rs.next();

			int no = rs.getInt(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			int hit = rs.getInt(4);
			String regDate = rs.getString(5);
			String name = rs.getString(6);
			int userNo = rs.getInt(7);

			boardVo = new BoardVo(no, title, content, hit, regDate, name, userNo);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardVo;

	}

	// 게시판 조회수 올리기
	public void boardHit(int titleNo) {

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " UPDATE board ";
			query += " set hit = hit+1 ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, titleNo);
			pstmt.executeQuery();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

	}

	// 게시판 등록
	public int boardInsert(BoardVo boardVo) {
		int count = -1;

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " insert into board ";
			query += " VALUES (seq_board_no.nextval, ?, ?, 0, SYSDATE, ?) ";

			pstmt = conn.prepareStatement(query);

			// 바인딩 값을 vo에 setter로 넣어준다
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUserNo());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	// 게시판 삭제
	public int boardDelete(int userNo, int boardNo) {

		int count = -1;

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " DELETE from board ";
			query += " where user_no = ? ";
			query += " and no = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	// 게시판 글 수정
	public int boardUpdate(int userNo, int titleNo, String title, String content) {

		int count = -1;

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " UPDATE board ";
			query += " set title = ? ";
			query += "     ,content = ? ";
			query += " where user_no = ? ";
			query += " and no = ? ";

			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, userNo);
			pstmt.setInt(4, titleNo);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			// System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;

	}

	// 게시판 검색
	public List<BoardVo> boardSearch(String keyword) {

		List<BoardVo> boardList = new ArrayList<BoardVo>();
		System.out.println(keyword);

		this.getConnect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select  bo.no, ";
			query += "         bo.title, ";
			query += "         bo.content, ";
			query += "         bo.hit, ";
			query += "         bo.reg_date, ";
			query += "         us.name, ";
			query += "         us.no ";
			query += " FROM board bo, users us ";
			query += " where bo.user_no = us.no ";
			query += " and title like ? ";
			query += " ORDER BY bo.no desc ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + keyword + "%");

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				String name = rs.getString(6);
				int userNo = rs.getInt(7);

				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, name, userNo);

				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return boardList;

	}
	*/

}
