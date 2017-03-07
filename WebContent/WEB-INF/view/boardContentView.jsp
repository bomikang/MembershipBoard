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
<style>
	textarea{margin-left:10px;}
</style>
<script>
	$(function(){
		$(".btnUpdateContent").click(function() {
			$("form[name='f1']").attr("action", "readArticle.do");
		});
		$(".btnDeleteContent").click(function() {
			$("form[name='f1']").attr("action", "deleteArticle.do");
		});
	});
</script>
</head>
<body>
	<c:set var="article" value="${subjectAndWriter}"></c:set>
	<c:set var="content" value="${boardContent}"></c:set>
	
	<a href="#" onclick="window.history.back();">[뒤로가기]</a>
	<a href="index.jsp">[메인으로]</a>
	<br /><br />
	
	<h1>게시글 보기</h1>
	<form action="" method="post" name="f1">
		<p>
			<label for="">제목</label>
			<input type="text" value="${article.title }" name="title" readonly="readonly"/>
		</p>
		<p>
			<label for="">작성자</label>
			<input type="text" value="${article.writer.name }" name="writer" readonly="readonly"/>
		</p>
		<p>
			<label for="">내용</label>
			<textarea cols="30" rows="10" name="content" readonly="readonly">${content.content }</textarea>
		</p>
			<!-- 매개변수 숨기기 -->
			<input type="hidden" name="no" value="${param.no}"/>
		<p>
			<input type="submit" value="수정하기" class="btnUpdateContent"/>
			<input type="submit" value="삭제하기" class="btnDeleteContent"/>
		</p>
	</form>
</body>
</html>