<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:import url="/WEB-INF/views/includes/asset.jsp"/>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<c:if test='${param.updatesuccess eq "true"}'> 
<script type="text/javascript">
	alert("바꾸신 정보로 다시 로그인 해주세요...")
</script>
</c:if>

<c:if test='${param.auth eq "false"}'> 
<script type="text/javascript">
	alert("정보 수정을 원하신다면 로그인부터 하세요...")
</script>
</c:if>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="<%=request.getContextPath()%>/user/auth">
					<label class="block-label" for="email">이메일</label> <input
						id="email" name="email" type="text" value=""> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">


					<c:if test='${result == "fail" }'>
						<p>로그인이 실패 했습니다.</p>
					</c:if>
					<button type="submit" class="btn"><i class="fas fa-sign-in-alt"> 로그인</i></button>
					
				</form>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>