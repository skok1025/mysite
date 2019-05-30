package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthAdminAccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		HttpSession session = request.getSession();
		
		if(session == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		UserVo authuser = (UserVo) session.getAttribute("authuser");
		
		if(authuser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		if(!"ADMIN".equals(authuser.getRole())) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;
		
	}
	
}
