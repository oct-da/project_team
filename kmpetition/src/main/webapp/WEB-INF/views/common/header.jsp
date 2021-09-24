<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
</script>

<c:if test='${not empty adminMessage }'>
	<script>
		window.onload = function() {
			alert("관리자 계정으로 로그인하셨습니다");
		}

	</script>
</c:if>
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
						<li><a href="${contextPath}/mypage/modMemberForm.do">회원정보수정</a></li>
						<li><a href="${contextPath}/mypage/myList.do">내가 쓴 글</a></li>
					</c:when>
					<c:when test="${isLogOn==true and isAdmin==true }">
						<li><a href="${contextPath}/admin/memberList.do">관리자페이지(미구현)</a></li>
						<li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
						<li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="#">고객센터</a></li>
				<li><a href="${contextPath}/board/searchForm.do">검색화면</a></li>
			</ul>
		</div>
		<br>
	</div>
</body>
</html>