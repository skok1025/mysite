package com.cafe24.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo)==1;
		
	}

	public Boolean insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert",vo) == 1;
	}
	
	
	public List<GuestbookVo> getList() {

		//List<GuestbookVo> result = new ArrayList<GuestbookVo>();
		List<GuestbookVo> result =  sqlSession.selectList("guestbook.getlist");
		return result;
	}



}
