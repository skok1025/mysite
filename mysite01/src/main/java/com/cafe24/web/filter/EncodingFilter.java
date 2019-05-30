package com.cafe24.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


@WebFilter(
		value="/*", 
		initParams = @WebInitParam(name="encoding", value="utf-8"))
public class EncodingFilter implements Filter {

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/* request 처리*/
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		
		
		
		/* response 처리*/
		
		
	}

	public void destroy() {
		
	}

	


}
