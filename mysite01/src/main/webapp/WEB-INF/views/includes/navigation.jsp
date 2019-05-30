<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="navigation">
	<ul>
		<c:choose>
			<c:when test='${param.menu == "main" }'>
				<li class="selected"><a href="<%=request.getContextPath()%>/">안대혁</a></li>
				<li><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath()%>/board">게시판</a></li>
			</c:when>
			<c:when test='${param.menu == "board" }'>
				<li><a href="<%=request.getContextPath()%>/">안대혁</a></li>
				<li><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
				<li class="selected"><a href="<%=request.getContextPath()%>/board">게시판</a></li>
			</c:when>
			<c:when test='${param.menu == "guestbook" }'>
				<li><a href="<%=request.getContextPath()%>/">안대혁</a></li>
				<li class="selected"><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath()%>/board">게시판</a></li>

			</c:when>
			<c:otherwise>
				<li><a href="<%=request.getContextPath()%>/">안대혁</a></li>
				<li><a href="<%=request.getContextPath()%>/guestbook">방명록</a></li>
				<li><a href="<%=request.getContextPath()%>/board">게시판</a></li>

			</c:otherwise>
		</c:choose>

	</ul>
</div>