<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${boardVo.title}</td>
					</tr>
					<tr>
						<td class="label">작성자</td>
						<td>${boardVo.user_name}</td>
					</tr>
					<tr>
						<td class="label">작성일</td>
						<td>${boardVo.reg_date}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(boardVo.content,newline,"<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write/${boardVo.no}">답글달기</a>
					<a href="${pageContext.servletContext.contextPath }/board">글목록</a>
					<c:if test="${authUser.no eq boardVo.user_no and authUser ne null}">
						<a href="${pageContext.servletContext.contextPath }/board/modify/${boardVo.no}">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>