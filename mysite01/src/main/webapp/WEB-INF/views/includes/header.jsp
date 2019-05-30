<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<div id="header">
	<h1>MySite</h1>
	<ul>
		<c:choose>
			<c:when test='${empty authuser }'>
			
		<li><a href="${pageContext.servletContext.contextPath }/user?a=loginform">로그인</a>
		<li>
		<li><a href="${pageContext.servletContext.contextPath }/user?a=joinform">회원가입</a>
		<li>				
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.servletContext.contextPath }/user?a=editform">회원정보수정</a>
		<li>
		<li><a href="${pageContext.servletContext.contextPath }/user?a=logout">로그아웃</a>
		<li>
		<li>${authuser.name }님 안녕하세요 ^^;</li>
				
			</c:otherwise>
		</c:choose>
		
		
		<%-- 
		<%
			if(authUser == null){
		%>
		<li><a href="<%=request.getContextPath()%>/user?a=loginform">로그인</a>
		<li>
		<li><a href="<%=request.getContextPath()%>/user?a=joinform">회원가입</a>
		<li>
		<%} else{%>
		<li><a href="<%=request.getContextPath()%>/user?a=editform">회원정보수정</a>
		<li>
		<li><a href="<%=request.getContextPath()%>/user?a=logout">로그아웃</a>
		<li>
		<li><%=authUser.getName()%>님 안녕하세요 ^^;</li>
		<%} %>
		 --%>
	</ul>
</div>