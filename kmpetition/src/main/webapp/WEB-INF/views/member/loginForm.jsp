<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<c:if test='${not empty message }'>
	<script>
		window.onload = function() {
			result();
		}

		function result() {
			alert("아이디나 비밀번호가 틀립니다. 다시 로그인해주세요");
		}
	</script>
</c:if>
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

				<tr>
					<td><input type="submit" value="로그인"> <input
						type="reset" value="다시입력"></td>
				</tr>
				<tr>
					<td><a href="${contextPath }/member/memberForm.do">회원가입창으로
							이동</a></td>
							<td><a href="${contextPath }/main/main.do">메인으로 이동</a></td>
				</tr>
				<tr>
					<td><a href="${contextPath }/member/findIdForm.do">아이디찾기</a></td>
					<td><a href="${contextPath }/member/findPwdForm.do">비밀번호
							찾기</a></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>