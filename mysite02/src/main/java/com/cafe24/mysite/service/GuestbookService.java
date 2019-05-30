package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public int addContent(GuestbookVo guestbookVo) {
		return guestbookDao.insert(guestbookVo)?1:0;
	}

	public List<GuestbookVo> getList() {
		return guestbookDao.getList();
	}

	public int deleteContent(GuestbookVo guestbookVo) {
		return guestbookDao.delete(guestbookVo)?1:0;
	}

	
	
}
