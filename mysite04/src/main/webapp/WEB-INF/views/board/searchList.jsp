<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/search" method="post">
					<input type="text" id="kwd" name="kwd" value="${kwd }">
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
					<c:set var='count' value='${fn:length(list)}'/>
					<c:forEach items="${map.list}" var="vo" varStatus="status">			
					<tr>
						<td>${status.index +1}</td>
						<td style="text-align:left; padding-left:${15 * vo.depth}px;">
							<c:if test="${vo.depth >0 }">
								<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
							</c:if>
							<a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">${vo.title }</a>
						</td>
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.reg_date }</td>
						<td>
							<c:if test="${authUser.no eq vo.user_no and authUser ne null}">
								<a href="${pageContext.servletContext.contextPath }/board/delete/${vo.no}" class="del" onclick="ondelete();">삭제</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>	
				</table>
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${-1 <= map.nowPage-map.paging.PAGE_PER_BLOCK-1}">
							<li><a href="${pageContext.servletContext.contextPath }/board/search?kwd=${kwd }&page=${map.nowPage - map.paging.PAGE_PER_BLOCK+1}">◀</a></li>
						</c:if>
						<c:forEach begin="${map.startPageBlock }" end="${map.endPageBlock}" step="1"
							var="num" varStatus="status">
							<c:if test="${map.nowPage eq num}">
							<li class="selected"><a href="${pageContext.servletContext.contextPath }/board/search?kwd=${kwd }&page=${num}">${num }</a></li>
							</c:if>
							<c:if test="${map.nowPage ne num }">
							<li><a
								href="${pageContext.servletContext.contextPath }/board/search?kwd=${kwd }&page=${num}">${num}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${map.paging.totalPageNum >= map.nowPage + map.paging.PAGE_PER_BLOCK -1}">
						<li><a href="${pageContext.servletContext.contextPath }/board/search?kwd=${kwd }&page=${map.nowPage + map.paging.PAGE_PER_BLOCK -1}">▶</a></li>
						</c:if>

					</ul>
				</div>					
				<!-- pager 추가 -->
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>
<script>
	function ondelete(){
		alert('정말 삭제하시겠습니까?');
	}
</script>