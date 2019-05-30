package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle
			(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		// DefaultServletHttpRequestHandler
		// 1. 핸들러 종류 확인  이미지 같은게 들어온다
		if(handler instanceof HandlerMethod == false) {
			System.out.println(handler);
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Method 에 @Auth 없으면
		//    Class(Type)에 @Auth 를 받아오기
		//if(auth == null) {
		// auth = ...
		//}
		
		
		// 5. @Auth가 안 붙어있는 경우
		if(auth == null) {
			return true;
		}
		
		// 6. @Auth (클래스 또는 메소드에) 가 붙어있기 때문에
		//    인증여부체크
		HttpSession session = request.getSession();
		
		if(session == null) { // 인증이 안됨
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authuser =(UserVo) session.getAttribute("authuser");
		
		if(authuser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		// 7. Role 가져오기
		Auth.Role role = auth.role();
		
		// 8. role이 Auth.Role.USER 라면
		//    인증된 모든 사용자는 접근 가능
		
		//System.out.println(auth.role().equals(authuser.getRole()));
		if(role == Auth.Role.USER) {
			return true;
		}
		
//		if(role == Auth.Role.ADMIN) {
//			return auth.role().equals(authuser.getRole());
//		}
		// 9. Admin Role 권한 체크
		// authUser.getRole().equal("ADMIN")
		
		
		
		
		return true;
	}
}
