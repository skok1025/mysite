package com.cafe24.mysite.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestBookVo;


@Repository
public class GuestBookDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean delete(GuestBookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return 1==count;
	}

	public boolean insert(GuestBookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return 1==count;
	}
	public List<GuestBookVo> getList() {
		
		return sqlSession.selectList("guestbook.getList");
	}

	
}
