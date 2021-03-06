package com.cafe24.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.SiteVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao admindao;
	
	@Autowired
	private UserDao userdao;
	
	private static final String SAVE_PATH = "/mysite-uploads";

	public int mainupdate(SiteVo siteVo) {
		String savefilename = siteVo.getAttach().getOriginalFilename();
		siteVo.setProfile(savefilename);
		byte[] fileData;
		try {
			fileData = siteVo.getAttach().getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + savefilename);
			os.write(fileData);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admindao.siteinsert(siteVo);
	}

	public void updateRole(UserVo uservo) {
		userdao.updateRole(uservo);
	}
	
}
