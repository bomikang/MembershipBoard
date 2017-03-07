<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			//input check
			if( !checkInputEmpty($("input[name]")) ){
				return false;
			}
			
			//when (password == confirmPassword)
			if($("input[name='password']").val() != $("input[name='confirmPassword']").val()){
				var next = $("input[name='confirmPassword']").nextAll(".error").eq(1);
				next.css("display", "block");
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="join.do" method="post" name="f1">
		<p>
			<label for="">아이디</label>
			<input type="text" name="id" />
			<span class="error">ID를 입력하세요</span>
		</p>
		
		<p>
			<label for="">이름</label>
			<input type="text" name="name" />
			<span class="error">이름을 입력하세요</span>
		</p>
		
		<p>
			<label for="">암호</label>
			<input type="password" name="password" />
			<span class="error">암호를 입력하세요</span>
		</p>
		
		<p>
			<label for="">암호 확인</label>
			<input type="password" name="confirmPassword" />
			<span class="error">암호 확인란을 입력하세요</span>
			<span class="error">암호와 확인란이 일치하지 않습니다</span>
		</p>
		
		<p>
			<input type="submit" value="가입" />
		</p>
	</form>
	
</body>
</html>