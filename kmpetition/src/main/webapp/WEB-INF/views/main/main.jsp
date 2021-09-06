<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	views/main/main.jsp
</h1>

<P>  
MainController에서 동작합니다.
</br>

<c:if test="${!isLogOn }">
<a href="${contextPath }/member/loginForm.do">로그인창으로 이동</a>
</c:if>

<c:if test="${isLogOn }">
<a href="${contextPath }/mypage/modMemberForm.do">회원정보 수정</a>
<a href="${contextPath }/member/logout.do">로그아웃</a>
</c:if>
 </P>
</body>
</html>
