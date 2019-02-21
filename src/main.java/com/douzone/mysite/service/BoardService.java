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
	/*public Map<String, Object> list(){
		List<BoardVo> list = boardDao.getList();
		int totalCount = boardDao.count();
		
		 pager 알고리즘
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalPageCount", toTalPageCount);
		
		return map;}
		*/
}