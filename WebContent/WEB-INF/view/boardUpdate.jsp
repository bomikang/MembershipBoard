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
</head>
<body>
	<c:set var="article" value="${subjectAndWriter}"></c:set>
	<c:set var="content" value="${boardContent}"></c:set>
	
	<a href="#" onclick="window.history.back();">[뒤로가기]</a>
	<a href="index.jsp">[메인으로]</a>
	<br /><br />
	
	<h1>게시글 수정</h1>
	<form action="updateArticle.do" method="post" name="f1">
		<p>
			<label for="">제목</label>
			<input type="text" value="${article.title }" name="title"/>
		</p>
		<p>
			<label for="">작성자</label>
			<input type="text" value="${article.writer.name }" name="writer" readonly="readonly"/>
		</p>
		<p>
			<label for="">내용</label>
			<textarea id="" cols="30" rows="10" name="content">${content.content}</textarea>
		</p>
			<!-- 매개변수 숨기기 -->
			<input type="hidden" name="no" value="${param.no}"/>
		<p>
			<input type="submit" value="수정하기" class="btnUpdateContent"/>
		</p>
	</form>
</body>
</html>