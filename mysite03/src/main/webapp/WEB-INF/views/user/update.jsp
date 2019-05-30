<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	UserVo vo = (UserVo)request.getAttribute("userVo");
 %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="updateForm" method="post" action="${pageContext.servletContext.contextPath }/user/update">
					
					<input type="hidden" name="no" value="${userVo.no }">
					<label class="block-label" for="name" >이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }"/>

					<label class="block-label" for="email">이메일</label>
					<label class="block-label" for="email">${userVo.email }</label>
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="${userVo.password }">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose >
						<c:when test='${userVo.gender eq "female" }'>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
						</c:when>
						<c:otherwise>
						<label>여</label> <input type="radio" name="gender" value="female" >
						<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						</c:otherwise>
						</c:choose>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>