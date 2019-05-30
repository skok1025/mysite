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

		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/list" method="get">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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
						<c:if test='${vo.isexist eq "exist" }'>
							<tr>
								<td>${count-status.index }</td>
								<td style="text-align: left;"><a
									style="padding-left: ${vo.depth*30}px;"
									href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}&kwd=${param.kwd}">
										<c:if test="${vo.depth != 0}">
											<i class="fas fa-arrow-circle-right"></i>
										</c:if> ${vo.title }
								</a></td>
								<td>${vo.memberName }</td>
								<td>${vo.hit }</td>
								<td>${vo.reg_date }</td>

								<td><c:if
										test="${sessionScope.authuser.no eq vo.memberNo }">
										<a
											href="${pageContext.servletContext.contextPath}/board/del?no=${vo.no}"
											class="del">삭제</a>
									</c:if> <c:if test="${sessionScope.authuser.no != vo.memberNo }">
										<a
											href="${pageContext.servletContext.contextPath}/board/write?replyno=${vo.no}"><i
											class="fas fa-comment-dots"></i></a>
									</c:if></td>
							</tr>
						</c:if>
						<c:if test='${vo.isexist eq "none"}'>
							<tr>
								<td>${count-status.index }</td>
								<td colspan="4" style="height: 60px;">삭제된 게시물입니다.</td>
							</tr>
						</c:if>
					</c:forEach>



				</table>

				<!-- pager 추가 -->
				${pagebar }
				<!-- pager 추가 -->



				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/write"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>