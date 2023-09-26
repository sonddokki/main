package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;		
	
	public List<GalleryVo> galleryList() {
		System.out.println("갤러리 불러오기 서비스");		
		return galleryDao.galleryList();
	}
	
	
	public String save(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("파일 저장 서비스");
		System.out.println(file.getOriginalFilename());		
		System.out.println(galleryVo);			
		
		// 0.파일 경로
			String saveDir = "C:\\\\javaStudy\\\\upload";
		
		// 1.파일관련 자료 추출/////////////////////////////////////////////////////////
			// (1) 오리지날네임 추출
			String orgName = file.getOriginalFilename();
			System.out.println(orgName);
			
			// (2) 확장자 추출
			String exName = orgName.substring(orgName.lastIndexOf("."));
	        // 몇번째에 "." 있는지 확인하는 메소드 + 그걸 잘라내고 추출하는 메소드
			System.out.println(exName);
			
			// (3) 저장파일명 (겹치지 않아야 한다)
			String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
					                // long + 문자열 + 확장자
			System.out.println(saveName);
			
			// (4) 파일 사이즈
			long fileSize = file.getSize();
			System.out.println(fileSize);
			
			// (5) 파일 경로
			String filePath = saveDir +"\\"+ saveName;
			System.out.println(filePath);
			
			// (6) Vo로 묶기
			galleryVo.setFilePath(filePath);
			galleryVo.setOrgName(orgName);
			galleryVo.setSaveName(saveName);
			galleryVo.setFileSize(fileSize);		
			
			
			System.out.println(galleryVo);
			
			// (7) Dao 만들어서 저장하기
			System.out.println("db에 저장 " + galleryVo);
			galleryDao.save(galleryVo);
			
		
		// 2.파일저장 (서버쪽 하드디스크에 저장) ////////////////////////////////////////////
			try {
				byte[] fileData;
				fileData = file.getBytes();
				
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return saveName;		
			
	}
	

	
	
	

}
