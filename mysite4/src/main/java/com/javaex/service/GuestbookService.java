package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestDao guestDao;

	// (1) 방명록 리스트
	public List<GuestVo> guestSelect() {
		List<GuestVo> guestList = guestDao.guestSelect();
		return guestList;
	}

	// (2) 방명록 등록
	public void listInsert(GuestVo guestVo) {
		guestDao.listInsert(guestVo);
	}

	// (3) 방명록 삭제
	public void listDelete(GuestVo guestVo) {
		guestDao.listDelete(guestVo);
	}

	// ajax방명록 등록
	public GuestVo addGuest(GuestVo guestVo) {
		System.out.println("ser addGuest");		
		guestDao.insertSelectKey(guestVo);
		
		int no = guestVo.getNo();
		System.out.println(guestVo.getNo());
		
		GuestVo gVo = guestDao.selectOne(no);
		System.out.println(gVo);
		return gVo;
	}

}
