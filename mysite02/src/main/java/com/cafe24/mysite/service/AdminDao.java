package com.cafe24.mysite.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.SiteVo;

@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlSession;

	public int siteinsert(SiteVo siteVo) {
		return sqlSession.insert("admin.siteinfoinsert", siteVo);
	}

	public SiteVo getsite() {
		return sqlSession.selectOne("admin.getsite");
	}
	
	
}
