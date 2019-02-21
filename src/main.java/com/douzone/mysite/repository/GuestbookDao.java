package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList(int page) {
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();

 		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

 		try {
			conn = getConnection();

 			// SQL문 준비
			String sql =
				"   select no," + 
				"          name," + 
				"	       message," + 
				"     	   date_format(reg_date, '%Y-%m-%d %h:%i:%s')" + 
				"     from guestbook" + 
				" order by reg_date desc" +
				"	  limit ?, 5";//5개까지 가져와서 보여주는거?

 			// Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*5);

 			rs = pstmt.executeQuery();

 			// 결과 가져오기(사용하기)
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String message = rs.getString(3);
				String reg_date = rs.getString(4);

 				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage( message );
				vo.setRegDate(reg_date);

 				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

 		return list;
	}
	
	public GuestbookVo delete( GuestbookVo guestbookVo ) {
			return sqlSession.selectOne("guestbook.delete",guestbookVo);
	}
	
	public long insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert",vo);
		long no = vo.getNo();//들어간다음에 나오면 no가 존재
		return no;
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			//1. 드라이버 로딩
			Class.forName( "com.mysql.jdbc.Driver" );
			
			//2. 연결하기
			String url="jdbc:mysql://localhost/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch( ClassNotFoundException e ) {
			System.out.println( "드러이버 로딩 실패:" + e );
		} 
		
		return conn;
	}	
}
