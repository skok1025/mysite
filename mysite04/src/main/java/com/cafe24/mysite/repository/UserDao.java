package com.cafe24.mysite.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;



@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Autowired
//	private DataSource dataSource;
	
	public UserDao() {
		System.out.println("userDao 생성");
	}
	public Boolean update(UserVo vo) {
		int count  = sqlSession.update("user.update",vo);
		return 1 == count;
	}

	public UserVo get(long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo get(String email, String password) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword",map);
		
		return userVo;
	}

	public boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert",vo);
		System.out.println(vo);
		return 1==count;
	}

}
