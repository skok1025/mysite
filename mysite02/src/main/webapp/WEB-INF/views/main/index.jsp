<%@page import="com.cafe24.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" style="width: 200px;"
						src="${pageContext.servletContext.contextPath }/saves/${siteVo.profile}"> 
					<h2>${siteVo.title }</h2>
					<h2>${siteVo.welcomeMessage}</h2>
					<p>
						${fn:replace(siteVo.description,newline,"<br>" )}
						<br><br /><br /> <a href="${pageContext.servletContext.contextPath }/guestbook/list">방명록</a>에
						글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>