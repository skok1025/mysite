package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		UserVo authUser = (UserVo)session.getAttribute("authuser");

		if (session != null && session.getAttribute("authuser")!= null) {
			session.removeAttribute("authuser"); // 메모리를 아끼기 위해서
			session.invalidate();
		}
		
		WebUtil.redirect(request, response, request.getContextPath());
	}

}
