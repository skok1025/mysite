package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//RetentionPolicy : 언제까지 유효하냐?

@Target({ElementType.TYPE,ElementType.METHOD})//어디에 붙여쓸꺼냐?
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

	public enum Role{//내부클래스
		USER,ADMIN
	}
	public Role role() default Role.USER;
	
	
//	String value() default "USER";
//	int test() default 1;
	
}
