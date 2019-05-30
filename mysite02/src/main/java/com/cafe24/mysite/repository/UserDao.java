package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	@Autowired
	DataSource dataSource;
	
	public UserDao() {
		System.out.println("UserDao Constructor!!!");
	}

	public UserVo get(Long no) {
		
		StopWatch sw = new StopWatch();
		sw.start();
		UserVo vo = sqlSession.selectOne("user.getByNo",no);
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		System.out.println(totalTime);
		return vo;
	}

	public UserVo get(UserVo vo)  {
	
		Map<String,String> map = new HashMap<String, String>();
//		map.put("email", vo.getEmail());
//		map.put("password", vo.getPassword());
//		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", vo);
		System.out.println(userVo);
		return userVo;
		
		
	}

	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		return 1 == count;
	}

	public Boolean update(UserVo vo) {
		return sqlSession.update("user.update",vo)==1;
	}

	public String getPassword(UserVo userVo) {
		return sqlSession.selectOne("user.getpw", userVo);
	}

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail",email);
	}


}
