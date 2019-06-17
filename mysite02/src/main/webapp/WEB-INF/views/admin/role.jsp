<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@page import="com.cafe24.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:import url="/WEB-INF/views/includes/asset.jsp" />

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />

<form action="${pageContext.servletContext.contextPath}/admin/role" method="post">
		<div id="content">
			<div id="board">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>권한</th>
						<th>권한바꾸기</th>
					</tr>
					<c:forEach items="${userlist }" var="uservo" varStatus="status">
						<tr>
						<input type="hidden" name="list[${status.index}].no" value="${uservo.no }" />
							<td>${uservo.no }</td>
							<td>${uservo.email }</td>
							<td>${uservo.role }</td>
							<td>
								<select name="list[${status.index}].role">
									<option value="USER" <c:if test="${uservo.role eq 'USER'}">selected</c:if>>유저</option>
									<option value="ADMIN" <c:if test="${uservo.role eq 'ADMIN'}">selected</c:if>>관리자</option>
								</select>
							</td>
						</tr>
					
					</c:forEach>
					
				</table>

				<div class="bottom">
					<button type="submit"
						id="new-book">적용</button>
				</div>
			</div>
		</div>
</form>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="role" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>