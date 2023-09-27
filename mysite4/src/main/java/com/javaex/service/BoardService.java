package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// (1)
	public List<BoardVo> boardSelect() {
		System.out.println("@Service");
		List<BoardVo> boardList = boardDao.boardSelect();
		return boardList;
	}

	
	// (3)
		public Map<String, Object> getBoardList3(int crtPage) {
			System.out.println("@Service 333");
			
			// 페이지당 글갯수
			int listCnt = 10;
			
			// 현재페이지 crtPage 파라미터 받는다
			// 없는 페이지면 1로 보낸다
			crtPage = (crtPage>0) ? crtPage : (crtPage=1);
			
//			if(crtPage>0) {				
//			} else {crtPage = 1;}
			
			// 시작글번호
			int startRNum = (crtPage-1)*listCnt+1;
			
			// 끝글번호
			int endRNum = (startRNum+listCnt)-1;			
			
			List<BoardVo> pList = boardDao.boardSelectList3(startRNum,endRNum);
			
			///////////////////////////////////////////
			// 페이징 계산
			
			// 페이지당 버튼 갯수
			int pageBtnCount = 5;
			
			// 전체 글 갯수
			int totalCnt = boardDao.selectTotalCnt();

			// 마지막버튼번호
			int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
			System.out.println(endPageBtnNo);
			
			// 시작버튼번호
			int startPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
			
			// 다음화살표 유무
			boolean next = false;
			if(listCnt * endPageBtnNo < totalCnt) {
				next = true;
			} else { // 다음버튼이 없을때
				endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);		
			}
			
			// 이전화살표 유무
			boolean prev = false;
			if(startPageBtnNo != 1) {
				prev = true;
			}
			
			/*
			System.out.println("==========================================");
			System.out.println(startPageBtnNo);
			System.out.println(endPageBtnNo);
			System.out.println(prev);
			System.out.println(next);
			*/
			
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("startPageBtnNo", startPageBtnNo);
			pMap.put("endPageBtnNo", endPageBtnNo);
			pMap.put("prev", prev);
			pMap.put("next", next);
			pMap.put("pList", pList);
			
			return pMap;
		}
	
}
