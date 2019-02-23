package com.douzone.mysite.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	public List<BoardVo> list(){
		return boardDao.getList();
	}
	public void delete(BoardVo boardVo) {
		boardDao.delete(boardVo);
	}
	public void insert(BoardVo boardVo) {
		boardDao.insert(boardVo);
	}
}