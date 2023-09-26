package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("fileupload")
public class GalleryController {
	
	@RequestMapping(value="form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("갤러리 폼");
		
		return "gallery/form";
	}
	
	

}
