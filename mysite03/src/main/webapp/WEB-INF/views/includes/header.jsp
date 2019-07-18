<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header">
	<h1>${siteVo.title }</h1>
	<ul>
		<sec:authorize access="isAuthenticated()">
			<li><a
				href="${pageContext.servletContext.contextPath }/user/update">회원정보수정</a>
			<li>
			<li><a
				href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a>
			<li>
			<li><sec:authentication property="principal.name"/> 님안녕하세요 ^^;</li>
		</sec:authorize>

		<sec:authorize access="!isAuthenticated()">
			<li><a
				href="${pageContext.servletContext.contextPath }/user/login">로그인</a>
			<li>
			<li><a
				href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
			<li>
		</sec:authorize>

	</ul>
</div>