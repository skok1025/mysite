package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public Boolean join(UserVo userVo) {
		return userDao.insert(userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

	public int update(UserVo userVo) {
		return userDao.update(userVo) ? 1 : 0;
	}

	public String getPassword(UserVo userVo) {
		return userDao.getPassword(userVo);
	}

	public Boolean existEmail(String email) {
		UserVo userVo = userDao.get(email);
		return userVo != null;
	}

	public UserVo getUser(Long no) {
		return userDao.get(no);
	}

}
