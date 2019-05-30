package com.cafe24.security;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true; //image,js파일이 여기에 들어온다. assets에 없는 애들
		}
		
		//2.casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		
		//3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		System.out.println("메서드에서 auth의 존재여부 : "+auth);
		
		//4. Method에 @Auth가 없으면 (인증이 필요 없는 메서드)
		if(auth == null) {
			//4.  Class(Type)에 @Auth를 받아오기
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			System.out.println("클래스에서 auth의 존재여부 : "+auth);
			if (auth == null) {
				return true;
			}
		}
		
		auth = handlerMethod.getMethodAnnotation(Auth.class);
		//5. Method에 @Auth가 없으면
		if(auth==null) {
			return true;
		}
		//6. @Auth가 (class 또는 method에)붙어 있기 때문에 인증 여부 체크
		HttpSession session= request.getSession();
		if(session == null) {//리턴이 안되어 있음
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//7. authUser 찾기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {//인증 x
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//8. Role 가져오기
		Auth.Role role = auth.role();
		
		//9.role이 Auth.Role.User라면 안증된 모든 사용자는 접근 가능
		if(role==Auth.Role.USER) {
			return true;
		}
		
		//10. Admin Role 권한 체크
//		authUser.getRole().equals("ADMIN");
		//Auth.Role.ADMIN이면 
		//authuser의 role를 체크 
		//user면 거부
		//admin이면 허용
		
		return true;//최종 목적은 컨트롤러에게 가기
	}
	
}
