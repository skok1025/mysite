<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="<%=request.getContextPath() %>/user">
					<input type="hidden" name="a" value="edit" />
					<input type="hidden" name="no" value="<%= ((UserVo)session.getAttribute("authuser")).getNo() %>" />
					<label class="block-label" for="email">이메일</label>
					<%= ((UserVo)session.getAttribute("authuser")).getEmail() %>
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					<label class="block-label" for="gender">성별</label>
					<select name="gender" id="gender">
						<option value="male">남자</option>
						<option value="female">여자</option>
					</select>
					<input type="submit" value="회원정보수정">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>