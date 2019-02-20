package com.douzone.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo userVo) {
		// 1. DB에 가입 회원 정보 insert 하기
		userDao.insert(userVo);
		// 2. email 주소 확인하는 메일 보내기 
	}
	
	public UserVo login(String email, String password) {
		return userDao.get(email, password);
	}

	public void logout(HttpSession session) {
		
	}

	public UserVo modifyselect(UserVo userVo) {
		return userDao.get(userVo.getNo());
	}

	public UserVo modify(UserVo userVo) {
		return userDao.update(userVo);
	}
	

	

}