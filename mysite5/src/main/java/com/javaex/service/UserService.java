package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	// (1) 로그인용 1명 가져오기
		public UserVo select(UserVo userVo) {
			return userDao.select(userVo);
		}

		// (2) 회원등록
		public void userInsert(UserVo userVo) {
			userDao.userInsert(userVo);
		}

		// (3) 회원수정용 1명 가져오기
		public UserVo userUpdateSelect(UserVo userVo) {
			return userDao.userUpdateSelect(userVo);
			
		}

		// (4) 회원수정
		public void update(UserVo userVo) {
			userDao.update(userVo);
		}	
		
		// (5) 중복체크		
		public boolean idCheck(String id) {
			UserVo userVo = userDao.selectUserOneById(id);
			
			if(userVo == null) {  // 해당 id 사용가능
				return true;
			} else {
				return false;	  // 중복 id 사용불가
			}					
		}

}
