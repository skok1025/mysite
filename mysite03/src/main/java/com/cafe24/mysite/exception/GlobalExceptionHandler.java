package com.cafe24.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request,HttpServletResponse response,Exception e) throws Exception{ 
		//어플리케이션 외적인 것이므로 기술침투까진 아니다?/ 예외를 처리해야 하므로 Exception이 필요하다.
		
		//1.로깅
		//e.printStackTrace();
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString()); //파일로도 남기고 화면에도 출력할 수 있다.
		System.out.println(errors.toString());
		
		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {//acpplication/json을 포함한 모든 문자
			//json 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			JSONResult jsonResult =JSONResult.fail(errors.toString());
			String result = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("utf-8"));
			os.flush();
			os.close();
			
		}else {
			//2.안내페이지 가기+정상적 종료(response)
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
			
		}
		
	}
	
}
