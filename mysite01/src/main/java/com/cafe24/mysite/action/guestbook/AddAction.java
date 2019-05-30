package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class AddAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("content");
		
		GuestbookVo v= new GuestbookVo();
		v.setName(name);
		v.setPassword(password);
		v.setContents(contents);
		
		System.out.println(new GuestbookDao().insert(v));
		WebUtil.redirect(request, response, request.getContextPath()+"/guestbook");
	}

}
