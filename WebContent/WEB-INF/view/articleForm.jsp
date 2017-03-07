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
<style>
	textarea{margin-left:10px; margin-top:5px;}
</style>
<script>
	$(function(){
		$("form[name='f1']").submit(function(){
			//input check
			if( !checkInputEmpty($("input[name]")) ){
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form action="write.do" method="post" name="f1">
		<p>
			<label for="">제목</label>
			<input type="text" name="title" />
			<span class="error">제목을 입력하세요.</span>
		</p>
		
		<p>
			<label for="">내용</label>
			<textarea name="content" cols="35" rows="10"></textarea>
		</p>
		
		<p>
			<input type="submit" value="등록" />
		</p>
	</form>
</body>
</html>