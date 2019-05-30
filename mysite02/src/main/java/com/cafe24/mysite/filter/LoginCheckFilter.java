package com.cafe24.mysite.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.vo.UserVo;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/board")
public class LoginCheckFilter implements Filter {

	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest httpRequest = 
								(HttpServletRequest) request;
		HttpServletResponse httpResponse = 
								(HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		if(session == null) {
			httpResponse.sendRedirect("http://localhost:8080/mysite2/login");
		}
		UserVo authuser = (UserVo) session.getAttribute("authuser");
		
		if(authuser == null) {
			httpResponse.sendRedirect("http://localhost:8080/mysite2/login");
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
