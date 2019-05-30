<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function(){
			$('#email').change(function(){
				$('#check-button').show();
				$('#check-image').hide();
			});
		$('#check-button').click(function(){
			var email = $('#email').val();
			if(email == ''){
				return;
			}
			// ajax 통신
			$.ajax({
				url : "${pageContext.servletContext.contextPath }/user/api/checkemail?email="+email, //문자열로 인식이 되는게 아니라 서버에서 el값으로 먼저 치환후 js통신을 한다.
				type : "GET",
				dataType : "json",
				data : "", //post방식일때 값을 여기에 넣어줌
				success:function(response){
					if(response.result != "success"){
						console.error(response.message);
						return;
					}
					if(response.data == true){
						alert('이미 존재하는 이메일 입니다.\n 다른 이메일을 사용해 주세요');
						$('#email').focus();
						$('#email').val("");
						return;
					}
					$('#check-button').hide();
					$('#check-image').show();
				},
				error : function(xhr, error){ //xmlHttpRequest?
						console.error("error : "+error);
				}
			});
		})
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form:form 	
						modelAttribute="userVo"
						id="join-form" name="joinForm" method="post" 
						action="${pageContext.servletContext.contextPath }/user/join">
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">
					
					<spring:hasBindErrors name="userVo">
    					<c:if test="${errors.hasFieldErrors('name') }">
    						<p style="font-weight:bold;color:red;text-align:left;padding:0;margin:0">
            					<spring:message code="${errors.getFieldError( 'name' ).codes[0] }"
            						text="${errors.getFieldError( 'name' ).defaultMessage }" /><!-- 코드에 해당안되는 에러메세지인 경우 defaultmessage출력 -->
         					</p>
   						</c:if>
					</spring:hasBindErrors>	
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id="check-button" value="체크">
					<img style="display: none" id="check-image" src="${pageContext.servletContext.contextPath }/assets/images/check.png"/>
					<p style="font-weight:bold;color:#f00;text-align:left;padding:0;margin:0"><form:errors path="email"/></p>
													
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					
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
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>