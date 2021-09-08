<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="2px">
<tr>
	<td>No</td>
	<td>아이디</td>
	<td>이름</td>
	<td>이메일</td>
	<td>전화번호</td>
	<td>최종접속일</td>
</tr>		


	<c:forEach var="member" items="${memberList }" varStatus="num">
<tr>
	<td>${num.count }</td>
	<td>${member.id }</td>
	<td>${member.name }</td>
	<td>${member.email }</td>
	<td>${member.phone }</td>
	<td>${member.lastlogin }</td>
</tr>		
	
	</c:forEach>
	
	</table>
</body>
</html>