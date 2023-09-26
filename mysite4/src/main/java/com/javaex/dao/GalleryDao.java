package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	// 갤러리 불러오기
	public List<GalleryVo> galleryList() {
		System.out.println("갤러리 불러오기 다오");		
		List<GalleryVo> galList = sqlSession.selectList("gallery.gallerySelect");
		System.out.println(galList);
		return galList;
	}

	// 파일 저장
	public void save(GalleryVo galleryVo) {
		System.out.println("파일저장 다오");
		System.out.println(galleryVo);
		sqlSession.insert("file.galleryInsert", galleryVo);
	}

}
