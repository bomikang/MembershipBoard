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
<script type="text/javascript">
	$(function(){
		$("form[name='f1']").submit(function(){
			if( !checkInputEmpty($("input[name]")) ){
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="login.do" method="post" name="f1">
		<p>
			<label for="">아이디</label>
			<input type="text" name="id" />
			<span class="error">ID를 입력하세요</span>
		</p>
		
		<p>
			<label for="">암호</label>
			<input type="password" name="password" />
			<span class="error">암호를 입력하세요</span>
		</p>
		
		<c:if test="${notJoin}">
			<span class="error2">등록된 회원이 없습니다. 회원가입을 해주세요.</span>
		</c:if>
		<c:if test="${idOrPwdNotMatch}">
			<span class="error2">아이디와 비밀번호가 일치하지 않습니다.</span>
		</c:if>
		
		<p>
			<input type="submit" value="로그인" />
		</p>
	</form>
</body>
</html>