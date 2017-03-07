<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${! empty auth}"><!-- 로그인 상태 ${auth}에 user가 들어있음-->
		<p>${auth.name}님, 반갑습니다.</p>
		<a href="logout.do">로그아웃</a>
		<a href="changepassword.do">비밀번호 변경</a>
		<a href="write.do">게시판 글쓰기</a>
		<a href="listArticle.do">게시판 목록보기</a>
	</c:if>
	<c:if test="${empty auth}"><!-- 로그아웃 상태 -->
		<a href="login.do">로그인</a>
		<a href="join.do">회원가입</a>
		<a href="list.do">회원리스트</a>
	</c:if>
</body>
</html>