<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">

</head>
<body>
	<h3>아이디 찾기 화면</h3>
	<form action="${contextPath}/member/findPwd.do" method="post">	
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">이름</td>
					<td><input type="text" name="name" id="name" size="20" /> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td><input type="text" name="id" id="id" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일</td>
					<td><input type="text" name="email" id="email" size="20" /></td>
				</tr>
				
		<tr >
			<td >
				<input type="submit"  value="찾기">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
		<tr>
			<td><a href="${contextPath }/member/memberForm.do">회원가입창으로 이동</a></td>
			<td><a href="${contextPath }/member/loginForm.do">로그인창으로 이동</a></td>
			<td><a href="${contextPath }/main/main.do">메인으로 이동</a></td>
		</tr>
		</tbody>
		</table>
</form>	
</body>
</html>