package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("fileupload")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping(value="form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("갤러리 폼");
		
		return "gallery/form";
	}	

	@RequestMapping(value="upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("파일업로드");
		
		// 파일 주소확인 (파일이 없어도 자동으로 만들어줌)
		//System.out.println(file);
		
		// 파일이 비었는지 묻는 메소드 ( 비었으면 true, 있으면 false )
		//System.out.println(file.isEmpty());
		
		// 파일의 처음 등록이름을 불러와줌
		//System.out.println(file.getOriginalFilename());
		
		// 서비스에게 파일 저장 시키기
		String saveName = fileService.save(file);
		
		model.addAttribute("saveName", saveName);
		
		
		return "gallery/result";
	}

}
