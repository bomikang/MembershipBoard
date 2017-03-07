<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	$(function(){
		$("form[name='f1']").submit(function() {
			if (! checkInputEmpty($("input[name]"))) {
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="changepassword.do" method="post" name="f1">
		<p>
			<label for="">현재 암호</label>
			<input type="password" name="password" />
			<span class="error">현재 암호를 입력하세요</span>
		</p>
		
		<p>
			<label for="">새 암호</label>
			<input type="password" name="newPassword" />
			<span class="error">새 암호를 입력하세요</span>
		</p>
		
		<c:if test="${pwdNotMatch}">
			<span class="error2">현재 암호가 일치하지 않습니다.</span>
		</c:if>
		
		<p>
			<input type="submit" value="비밀번호 변경" />
		</p>
	</form>
	
</body>
</html>