package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao guestBookDao;

	public List<GuestBookVo> getlist() {
		
		return guestBookDao.getList();
	}

	public Boolean add(GuestBookVo guestbookvo) {
		return guestBookDao.insert(guestbookvo);
	}

	public Boolean delete(GuestBookVo guestbookvo) {
		return guestBookDao.delete(guestbookvo);
	}
}
