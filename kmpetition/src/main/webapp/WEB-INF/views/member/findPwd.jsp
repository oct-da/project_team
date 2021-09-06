<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>views/member/findPwd.jsp</h1>

	<P>
		findPwd.do의 결과로 이동한 페이지
		</br> 
		비밀번호 찾기의 결과
		</br></br></br>

		<c:if test='${empty result }'>
			조회된 회원 정보가 없습니다.
			</br></br>
			<a href="${contextPath }/member/findIdForm.do">ID찾기 페이지로 돌아가기</a>
			</br></br>
			<a href="${contextPath }/member/findPwdForm.do">비밀번호 찾기 페이지로 돌아가기</a>
		</c:if>
		
		<c:if test='${not empty result }'>
			<form action="${contextPath}/member/modPwd.do" method="post">	
		<table>
		<h1>비밀번호를 변경해주세요.</h1>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">새 비밀번호 입력</td>
					<td><input type="text" name="pwd" id="pwd" size="20" /> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호 확인</td>
					<td><input type="text" name="_pwd" id="_pwd" size="20" /></td>
				</tr>
				<tr class="dot_line">
					<td><input type="hidden" name="id" id="id" size="20" /></td>
				</tr>
		<tr >
			<td >
				<input type="submit"  value="확인">
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
		</c:if>
		
	</br> </br>
	</P>
	<td><a href="${contextPath }/member/memberForm.do">회원가입창으로 이동</a></td>
</body>
</html>
