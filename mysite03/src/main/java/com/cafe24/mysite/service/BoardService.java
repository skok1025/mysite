package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PagingVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public Boolean write(BoardVo boardVo) {
		return boardDao.insert(boardVo);
	}

	public Long lastID() {
		return boardDao.lastID();
	}

	public BoardVo getView(long no) {
		return boardDao.getView(no);
	}
	
	public BoardVo getTitle(long no) {
		return boardDao.getTitle(no);
	}

	public Boolean reply(BoardVo boardVo) {
		//정렬 순서 update
		boardDao.updataByOrderNo(boardVo);
		//답글작성
		return boardDao.reply(boardVo);
	}
	
	//전체 리스트 페이징
	public Map<String,Object> getList(int page){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		PagingVo pagingvo = new PagingVo(boardDao.getTotalRowNum(), page);
		List<BoardVo> list= boardDao.getList(pagingvo);
		//현재 페이지 첫번째 블럭
		int startPageBlock = (page<=pagingvo.getPAGE_PER_BLOCK()/2)?1:page-pagingvo.getPAGE_PER_BLOCK()/2;
		//현재 페이지 마지막 블럭
		int endPageBlock =  page+pagingvo.getPAGE_PER_BLOCK()/2;
		if(startPageBlock==1) {
			endPageBlock = pagingvo.getPAGE_PER_BLOCK();
		}else if(page+pagingvo.getPAGE_PER_BLOCK()-1 > pagingvo.getTotalPageNum()) {
			startPageBlock = pagingvo.getTotalPageNum()-pagingvo.getPAGE_PER_BLOCK()+1;
			endPageBlock = pagingvo.getTotalPageNum();
		}
		
		
		map.put("paging", pagingvo);
		map.put("list", list);
		map.put("nowPage",page);
		map.put("endPageBlock", endPageBlock);
		map.put("startPageBlock", startPageBlock);
		
		return map;
	}

	public Boolean modify(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}

	public Boolean delete(Map map) {
		return boardDao.delete(map);
	}

	public Long getUser(long no) {
		return boardDao.getUser(no);
	}
	
	public Boolean updateByHit(long no) {
		return boardDao.updateByHit(no);
	}

	public Map<String, Object> getSearch(String kwd,int page) {
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("kwd", kwd);
		
		PagingVo pagingvo = new PagingVo(boardDao.getKwdTotalRowNum(kwd), page);
		paramMap.put("paging", pagingvo);
		List<BoardVo> list= boardDao.getSearch(paramMap);
		//현재 페이지 첫번째 블럭
		int startPageBlock = (page<=pagingvo.getPAGE_PER_BLOCK()/2)?1:page-pagingvo.getPAGE_PER_BLOCK()/2;
		//현재 페이지 마지막 블럭
		int endPageBlock =  page+pagingvo.getPAGE_PER_BLOCK()/2;
		
		
		
		if(startPageBlock==1) {
			if(pagingvo.getTotalPageNum()<pagingvo.getPAGE_PER_BLOCK()) {
				endPageBlock = pagingvo.getTotalPageNum();
			}else {
				endPageBlock = pagingvo.getPAGE_PER_BLOCK();
			}
		}else if(page+pagingvo.getPAGE_PER_BLOCK()-1 > pagingvo.getTotalPageNum()) {
			startPageBlock = pagingvo.getTotalPageNum()-pagingvo.getPAGE_PER_BLOCK()+1;
			endPageBlock = pagingvo.getTotalPageNum();
		}
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("paging", pagingvo);
		resultMap.put("list", list);
		resultMap.put("nowPage",page);
		resultMap.put("endPageBlock", endPageBlock);
		resultMap.put("startPageBlock", startPageBlock);
		
		return resultMap;
	}
	
}
