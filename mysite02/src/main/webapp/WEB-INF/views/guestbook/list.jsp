<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cafe24.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
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
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath() %>/guestbook/list" method="post">
					
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="contents"></textarea></td>
						</tr>
						<tr>
						
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
				
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list }" var="vo" varStatus="status">
					<li>
						<table>
							<tr>
								<td>[${count-status.index}]</td>
								<td>${vo.name }</td>
								<td>${vo.regDate }</td>
								<td><a href="<%=request.getContextPath()%>/guestbook/delete/${vo.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.contents,newline,"<br>") } 
								</td> 
								
								<%-- <td colspan="4">${vo.contents }</td> --%>
							</tr>
						</table> <br>
					</li>
	</c:forEach>
				
				</ul>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="guestbook" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>