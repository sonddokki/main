package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping("gallery")
public class GalleryController {	
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("갤러리 리스트");
		
		List<GalleryVo> galList = galleryService.galleryList();
		System.out.println(galList);
		
		model.addAttribute("galList" ,galList);
		
		return "gallery/list";
	}	
	
	@RequestMapping(value="upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="image") MultipartFile file
						,@RequestParam(value="content") String content
						,HttpSession session) {
		System.out.println("파일업로드");
		
		System.out.println(file.isEmpty());
		System.out.println(file.getOriginalFilename());
		System.out.println(content);
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println(authUser.getNo());
		
		GalleryVo galleryVo = new GalleryVo();
		galleryVo.setUserNo(authUser.getNo());
		galleryVo.setUserName(authUser.getName());
		galleryVo.setContent(content);
		
		System.out.println(galleryVo);
		galleryService.save(file, galleryVo);
		
		// 세션아이디 추가
		
		// 파일 주소확인 (파일이 없어도 자동으로 만들어줌)
		//System.out.println(file);
		
		// 파일이 비었는지 묻는 메소드 ( 비었으면 true, 있으면 false )
		//System.out.println(file.isEmpty());
		
		// 파일의 처음 등록이름을 불러와줌
		//System.out.println(file.getOriginalFilename());
		
		// 서비스에게 파일 저장 시키기
		//galleryService.save(file);
		
		return "redirect:list";
	}
	

}
