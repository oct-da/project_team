<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
</script>

<body>
	<div id="header" style="background-color:yellowgreen">
		<div id="logo">
			<a href="${contextPath}/main/main.do"> 메인으로 </a>
		</div>
		<div id="head_link">
		<p>header</p>
			<ul>
				<c:choose>
					<c:when test="${isLogOn==true and not empty memberInfo }">
						<li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
						<li><a href="${contextPath}/mypage/modMemberForm.do">마이페이지</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
						<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="#">고객센터</a></li>
			</ul>
		</div>
		<br>
	</div>
</body>
</html>