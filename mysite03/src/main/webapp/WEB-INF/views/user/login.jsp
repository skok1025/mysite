<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<sec:csrfMetaTags/>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">
	// Ajax 통신 중에 csrf 확인용 
	var csrfParameter = $('meta[name="_csrf_parameter"]').attr('content');
	var csrfHeader = $('meta[name="_csrf_header"]').attr('content');
	var csrfToken = $('meta[name="_csrf"]').attr('content');
	console.log(csrfParameter)
	console.log(csrfHeader)
	console.log(csrfToken)
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="${pageContext.servletContext.contextPath }/user/auth">
					<sec:csrfInput/>
					
					<label class="block-label" for="email">이메일</label> 
					<input name="email" id="email" name="email" type="text" value="">					
					<label class="block-label" for="password">패스워드</label> 
					<input name="password" id="password" type="password" value=""> 
					<label class="block-label" for="remember-me" >자동로그인</label> 
					<input id="remember-me" name="remember-me" type="checkbox"> 
					<input type="submit" value="로그인">
				</form>
				<c:if test='${result eq "fail" }'>
					<p>로그인이 실패 했습니다.</p>
				</c:if>
			</div>

		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>

</body>
</html>