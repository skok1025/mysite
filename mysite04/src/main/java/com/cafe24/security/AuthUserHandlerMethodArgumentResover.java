package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResover implements HandlerMethodArgumentResolver {


	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED; //@AuthUser를 붙인 객체(controller.update.authUser)에 저장이 된다.
		}
		
		HttpServletRequest request= webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		
		return session.getAttribute("authUser");
	}
	

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안 붙어 있음
		if(authUser == null) {
			return false;
		}
		
		if(parameter.getParameterType().equals(UserVo.class)==false) {//클래스 객체들을 비교함
			return false;
		}
		
		
		return true;
	}

}
