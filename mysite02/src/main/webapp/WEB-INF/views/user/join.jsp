<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<c:import url="/WEB-INF/views/includes/asset.jsp" />
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">

<script
	src="<%=request.getContextPath()%>/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {

		$("#email").change(function() {
			$("#check_bt").show();
			$("#check-image").hide();
		});

		$("#check_bt")
				.click(
						function() {
							var email = $("#email").val();
							if (email == '') {
								return;
							}

							/*ajax 통신*/
							$
									.ajax({
										url : "${pageContext.servletContext.contextPath}/user/api/checkemail?email="
												+ email,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {
											//console.log(response);
											if (response.result != 'success') {
												console.log(response);
												//console.error(response.message);
												return;
											}

											if (response.data == true) {
												alert("이미 존재하는 이메일 입니다. \n다른 이메일을 사용하세요.");
												$("#email").focus().val("");

												return;
											}

											$("#check_bt").hide();
											$("#check-image").show();

										},
										error : function(xhr, error) {
											console.error("error:" + error);
										}
									});

						});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">

				<form:form 
				modelAttribute="userVo"
				id="join-form" 
				name="joinForm" 
				method="post"
				action="${pageContext.servletContext.contextPath }/user/join">
					<input type="hidden" name="a" value="join" /> <label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <br />
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="font-weight: bold; color: tomato; text-align: left;">
								<spring:message
									code="${errors.getFieldError( 'name' ).codes[0] }"
									text="${errors.getFieldError( 'name' ).defaultMessage }" />

							</p>
						</c:if>
					</spring:hasBindErrors>



					<label class="block-label" for="email">이메일</label> 
					<form:input id="email" name="email" type="text" value="" path="email"/> 
					<input id="check_bt" type="button" value="중복체크"> 
					<img id="check-image" style="display: none;"
						src="${pageContext.servletContext.contextPath}/assets/images/check.png"
						alt="체크" /> <br />
					<p style="font-weight: bold; color: tomato; text-align: left; padding: 0px; margin: 0px;">
						<form:errors path="email"/>
					</p>
<%-- 
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('email') }">
							<p style="font-weight: bold; color: red; text-align: left;">
								<spring:message
									code="${errors.getFieldError( 'email' ).codes[0] }"
									text="${errors.getFieldError( 'email' ).defaultMessage }" />

							</p>
						</c:if>
					</spring:hasBindErrors>

 --%>
					<label class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <form:radiobutton path="gender" value="female" checked="checked"/> 
						<label>남</label> <form:radiobutton path="gender" value="male"/>
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<button type="submit">
						<i class="fas fa-user-plus">가입하기</i>
					</button>

				</form:form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>