package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapController {
	
	
	@RequestMapping(value = "/map", method = { RequestMethod.GET, RequestMethod.POST })
	public String hello(){
		System.out.println("맵");
		
		return "map/map";
	}	
	
	@RequestMapping(value = "/map2", method = { RequestMethod.GET, RequestMethod.POST })
	public String hello2(){
		System.out.println("현재 내 위치");
		
		return "map/map2";
	}	

}
