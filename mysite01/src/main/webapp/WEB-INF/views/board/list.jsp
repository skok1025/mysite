<%@page import="com.cafe24.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    

  
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp'/>
			
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					
					<c:set var="count" value="${fn:length(list) }"></c:set>
					<c:forEach varStatus="status" var="vo" items="${list }">
						
						<tr>
							<td>${count-status.index }</td>
							<td><a href="${pageContext.servletContext.contextPath}/board?a=view&no=${vo.no}">${vo.title }</a></td>
							<td>${vo.memberName }(${vo.memberEmail })</td>
							<td>${vo.cnt }</td>
							<td>${vo.regDate }</td>
							<td><a href="${pageContext.servletContext.contextPath}/board?a=del&no=${vo.no}" class="del">삭제</a></td>
						</tr>
					</c:forEach>
					
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>