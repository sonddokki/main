package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.controller.GuestBookController;
import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestVo;
import com.javaex.vo.JsonResultVo;

@Controller
@RequestMapping("/api")
public class ApiGuestBookController {

	@Autowired
	private GuestBookController guestBookController;

	@Autowired
	GuestbookService guestbookService;

	// 방명록 메인 화면
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList() {
		System.out.println("api/addList");

		return "guestbook/listAjax";
	}

	// 방명록 데이터만 가져오기 ajax
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestVo> list() {
		System.out.println("api/list");

		// 전체방명록 데이터 가져오기
		List<GuestVo> guestBookList = guestbookService.guestSelect();
		System.out.println(guestBookList);

		return guestBookList;
	}

	// 방명록 ajax
	@ResponseBody // 한명 값 바디로 넣어주기
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestVo add(@ModelAttribute GuestVo guestVo) {
		System.out.println("api/add");

		System.out.println("등록할 값 " + guestVo);

		GuestVo gVo = guestbookService.addGuest(guestVo);

		System.out.println("등록된 값 " + gVo);

		return gVo;
	}

	// 방명록 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("api/delete");

		guestbookService.listDelete(guestVo);

		return "";
	}
	
	
	////////////////////////////////////////////////////////////////////
	
	//방명록 메인 화면
		@RequestMapping(value="/addList2", method= {RequestMethod.GET, RequestMethod.POST})
		public String addList2() {
			System.out.println("ApiGuestbookController.addList2()");
			
			
			return "guestbook/listAjax2";
		}
		
		
		//요청데이터를 json으로 받기
		@ResponseBody
		@RequestMapping(value="/add2", method= {RequestMethod.GET, RequestMethod.POST})
		public JsonResultVo add2(@RequestBody GuestVo guestbookVo) {
			System.out.println("ApiGuestbookController.add2()");
			System.out.println(guestbookVo);
			
			//service 처리
			GuestVo gVo = guestbookService.addGuest(guestbookVo);
			
			JsonResultVo jsonResultVo= new JsonResultVo();
			jsonResultVo.success(gVo);
			
			return jsonResultVo; 
			
		}
		
		
		//복잡한 데이터 전송 테스트
		@RequestMapping(value="/add3", method= {RequestMethod.GET, RequestMethod.POST})
		public String add3(@RequestBody List<GuestVo> guestbookList) {
			System.out.println("ApiGuestbookController.add3()");
			System.out.println(guestbookList);
			
			
			return null;
		}
		

}
