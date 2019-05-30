package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PagingVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BoardVo boardVo) {
		int count = sqlSession.insert("board.insert", boardVo);
		return 1==count;
	}
	public Boolean updataByOrderNo(BoardVo boardVo) {
		int count = sqlSession.update("board.updateByOrderNo", boardVo);
		return 1==count;
	}
	public Long lastID() {
		return sqlSession.selectOne("board.lastID");
	}
	public BoardVo getView(long no) {
		return sqlSession.selectOne("board.getView", no);
	}
	public BoardVo getTitle(long no) {
		return sqlSession.selectOne("board.getTitle", no);
	}
	public Boolean reply(BoardVo boardVo) {
		int count = sqlSession.insert("board.reply", boardVo);
		return 1==count;
	}
	//전체리스트
	public List<BoardVo> getList(PagingVo vo){
		return sqlSession.selectList("board.getList",vo);
	}
	public int getTotalRowNum() {
		return sqlSession.selectOne("board.getTotalRowNum");
	}
	public Boolean update(BoardVo boardVo) {
		int count = sqlSession.update("board.update", boardVo);
		return 1==count;
	}
	public Boolean delete(Map map) {
		int count = sqlSession.delete("board.delete",map);
		return count==1;
	}
	public Long getUser(long no) {
		return sqlSession.selectOne("board.getUser", no);
	}
	public Boolean updateByHit(long no) {
		int count = sqlSession.update("board.updateByHit",no);
		return count == 1;
	}
	public List<BoardVo> getSearch(Map<String, Object> paramMap) {
		return sqlSession.selectList("board.search", paramMap);
	}
	public int getKwdTotalRowNum(String kwd) {
		return sqlSession.selectOne("board.getKwdTotalRowNum", kwd);
	}
}
