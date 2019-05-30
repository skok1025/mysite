package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.service.AdminDao;
import com.cafe24.mysite.vo.SiteVo;

@Service
public class MainService {

	@Autowired
	private AdminDao adminDao;

	public SiteVo showcurrentSite() {

		return adminDao.getsite();
	}
}
