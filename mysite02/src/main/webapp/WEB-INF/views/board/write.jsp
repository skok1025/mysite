<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form:form 
					modelAttribute="boardVo"
					class="board-form" method="post" enctype="multipart/form-data"
					action="${pageContext.servletContext.contextPath }/board/write">
					
					<input type="hidden" name="memberNo" value="${authuser.no }" />
					<c:if test="${not empty param.replyno }">
						<input type="hidden" name="replyno" value="${param.replyno }" />
					</c:if>
					<c:if test="${empty param.replyno }">
						<input type="hidden" name="replyno" value="0" />
					</c:if>
					<table class="tbl-ex">
						<tr>
							<th colspan="2"><c:if test="${not empty param.replyno}"> 답글 <br /> </c:if>${authuser.name}(${authuser.email })의글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>
							<form:input path="title" name="title"/> <br /> 
							<p style="color:tomato;"><form:errors path="title"/></p>
							</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="contents"></textarea></td>
						</tr>
						<tr>
							<td class="label">첨부파일</td>
							<td><input type="file" name="attach" /></td>
						</tr>
					</table>
					<div class="bottom">
						<a href="/board">취소</a> <input type="submit" value="등록">
					</div>
				</form:form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>