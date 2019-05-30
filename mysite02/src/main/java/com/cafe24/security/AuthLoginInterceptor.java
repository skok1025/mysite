package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AuthLoginInterceptor-preHandle");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ApplicationContext ac = 
					WebApplicationContextUtils.
					getWebApplicationContext(request.getServletContext());
		
		UserService userService = ac.getBean(UserService.class);
				
		UserVo userVo = new UserVo();

		userVo.setEmail(email);
		userVo.setPassword(password);
		
		UserVo authuser = userService.getUser(userVo);
		
		if(authuser==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		// 세션 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authuser", authuser);
		response.sendRedirect(request.getContextPath());
				
		return false;
	}
}

