<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	table{
		width:500px;
	}
	table, th, td{
		border:1px solid black;
		border-collapse: collapse;
		text-align: center;
	}
</style>
</head>
<body>
	<a href="index.jsp">메인으로</a><br><br>
	<c:if test="${viewList.size() == 0}">
		<p>등록된 member가 없습니다.</p>
	</c:if>
	<c:if test="${viewList.size() > 0}">
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>비밀번호</th>
				<th>등록날짜</th>
			</tr>
			<c:forEach var="member" items="${viewList}">
				 <tr>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td>${member.password}</td>
					<%-- <td>${member.regDate}</td> --%>
					<td><fmt:formatDate value="${member.regDate}" type="date" dateStyle="short"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>