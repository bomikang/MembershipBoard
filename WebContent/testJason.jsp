<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(function(){
		$.ajax({
			url:"jsontest.do",
			type:"post",
			timeout:30000,
			dataType:"json",
			data:{"no":3}, //매개 변수 no에 데이터 3 보내기
			success:function(data){
				alert("success");
				console.log(data);
				$("#test").html("제목 : "+ data.title +"<br>"+ "작성자 id : "+ data.writer.id +"<br>"+ "작성자 이름 : "+ data.writer.name);
			}
		});
	});
</script>
</head>
<body>
	<h1>Json 테스트</h1>
	<div id="test">
		
	</div>
</body>
</html>