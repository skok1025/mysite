package com.cafe24.mysite.interceptor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.URLEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor01 implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyInterceptor01:preHandle()");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/*
		 * System.out.println("MyInterceptor01:postHandle()");
		 */
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		 System.out.println("MyInterceptor01:afterCompletion()");
		
		 Enumeration<String> param = request.getParameterNames();
	        String strParam = ""; 
	        while(param.hasMoreElements()) { 
	            String name = (String)param.nextElement(); 
	            String value = request.getParameter(name); 
	            strParam += name + "=" + value + "&"; 
	        }
	        String URL = strParam.equals("")? request.getRequestURL().toString():request.getRequestURL() + "?" + strParam; 
		 
	        System.out.println("====================");
			System.out.println("now request: "+URL);
			System.out.println("====================");
			
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/log_request.txt",true),"UTF-8"));
				String requestUri = URLDecoder.decode(URL, "utf-8");
				bw.write(requestUri+"\n");
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

	
}
