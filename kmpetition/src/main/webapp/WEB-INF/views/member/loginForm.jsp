<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

</head>
<body>
	<h3>로그인화면</h3>
	<form action="${contextPath}/member/login.do" method="post">	
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td><input type="text" name="id" id="id" size="20" /> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비번</td>
					<td><input type="password" name="pwd" id="pwd" size="20" /></td>
				</tr>
				
		<tr >
			<td >
				<input type="submit"  value="로그인">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
		</tbody>
		</table>
</form>	
</body>
</html>