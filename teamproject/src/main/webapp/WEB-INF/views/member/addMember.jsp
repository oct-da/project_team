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
	<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">	
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="id"  id="id"  size="20" />
					  <input type="button"  id="btnOverlapped" value="중복체크"  />
					</td>
					<td>
					  <input type="text" name="pw"  id="pw"  size="20" />
					</td>
					<td>
					  <input type="text" name="name"  id="name"  size="20" />
					</td>
					<td>
					  <input type="text" name="phone"  id="phone"  size="20" />
					</td>
					<td>
					  <input type="text" name="email"  id="email"  size="20" />
					</td>
				</tr>
				
		<table align=center>
		<tr >
			<td >
				<input type="submit"  value="회원 가입">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
	</table>
</form>	
</body>
</html>