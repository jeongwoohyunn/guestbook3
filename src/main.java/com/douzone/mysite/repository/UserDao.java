package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserVo get(long no) {
		return null;
	}

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo update(UserVo userVo) {
		return sqlSession.selectOne("user.update", userVo);
	}

	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVo get(String email, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}

	public int insert(UserVo vo) {
		// namspace의 id
		int count = sqlSession.insert("user.insert", vo);
		return count;
		// return count = sqlSession.insert("user.insert",vo);
	}

}