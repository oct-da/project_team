<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="py-5 text-center">
		<h2>게시판</h2>
	</div>

	<h3>파일이 업로드 되었습니다.</h3>
	<h3>map.fileList를 items로 한 forEach문</h3>
	<c:forEach var="file" items="${map.fileList}" varStatus="status">


		<span>${file}</span>

	</c:forEach>
	</br>

	</br>


</body>
</html>