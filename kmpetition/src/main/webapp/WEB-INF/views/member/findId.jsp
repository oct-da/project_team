<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>views/member/findId.jsp</h1>

	<P>
		findId.do의 결과로 이동한 페이지
		</br> 
		id찾기의 결과
		</br>

		<c:if test='${empty findId }'>
			조회된 회원 정보가 없습니다.
			</br></br>
			<a href="${contextPath }/member/findIdForm.do">ID찾기 페이지로 돌아가기</a>
		</c:if>
		<c:if test='${not empty findId }'>
			당신의 ID는  <strong>${findId }</strong>
		</c:if>
		
	</br> </br>
	</P>
	<td><a href="${contextPath }/main/main.do">메인으로 이동</a></td>
	<td><a href="${contextPath }/member/memberForm.do">회원가입창으로 이동</a></td>
	<td><a href="${contextPath }/member/loginForm.do">로그인창으로 이동</a></td>
</body>
</html>
