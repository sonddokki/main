package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 생성자, 메소드 gs

	// 메소드 일반

	// (1) 방명록 리스트
	public List<GuestVo> guestSelect() {
		System.out.println("dao");
		List<GuestVo> guestList = sqlSession.selectList("guest.selectGuest");
		System.out.println(guestList);
		return guestList;
	}

	
	
	// (2) 방명록 등록
	
	
	
	
	// (3) 방명록 삭제
	
}
