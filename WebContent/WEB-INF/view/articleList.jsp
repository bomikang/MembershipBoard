<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/common.css" />
<style>
	table{ width:500px;}
	table,th,td{border:1px solid black; border-collapse: collapse;}
</style>
</head>
<body>
	<a href="index.jsp">메인으로</a>
	<c:if test="${viewContentList.size() == 0 }">
		<p>등록된 게시글이 없습니다.</p>
	</c:if>
	<c:if test="${viewContentList.size() > 0 }">
		<table>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="content" items="${viewContentList}">
				<tr>
					<td>${content.number }</td>
					<td><a href="readArticle.do?no=${content.number}">${content.title }</a></td>
					<td>${content.writer.name }</td>
					<td>${content.readCount }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>