package com.cafe24.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor02 extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
																						//handlermapping에 있는 controller에 정보를 받음 - 기술 침투 아님 
		
		System.out.println("MyInterceptor02 preHandle() called....");
		return true;
	}

}
