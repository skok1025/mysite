<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="${pageContext.servletContext.contextPath }/user/update">
					<input type="hidden" name="no" value="${sessionScope.authuser.no}" />
					<label class="block-label" for="email">이메일</label>
					${sessionScope.authuser.email} <label class="block-label">패스워드</label>
					<input name="password" type="password"
						value="${requestScope.password }"> <label
						class="block-label" for="gender">성별	</label> 
						<select name="gender" id="gender">
						<c:if test='${sessionScope.authuser.gender eq "male" }'>
							<option value="male" selected>남자</option>
							<option value="female">여자</option>
						</c:if>
						<c:if test='${sessionScope.authuser.gender eq "female" }'>
							<option value="male">남자</option>
						<option value="female" selected>여자</option>
						</c:if>
						</select> 
					<input type="submit" value="회원정보수정" class="submit_bt" style="width: 100px;">
				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>