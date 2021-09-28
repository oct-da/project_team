<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>아이디 확인</title>

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" type="text/css">

    <!-- jQuery, js -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>


<c:if test='${not empty findId }'>
<form name="frmFoundId" method="post" action="#" enctype="UTF-8">
        <div class="wrap">
            <div class="foundId">
                <h2>아이디 확인</h2>
                <div class="info">
                    <p>회원님의 아이디는 <strong>${findId}</strong>입니다.</p>
                </div>
                <div class="submit4">
                    <a href="${contextPath}/member/findPwdForm.do"><input type="button" id="goFindPw" value="비밀번호 찾기" style="cursor:pointer"></a>
                    <a href="${contextPath}/member/loginForm.do"><input type="button" id="goLogin" value="로그인" style="cursor:pointer"></a>
                </div>
            </div>
        </div>
    </form>
</c:if>




<c:if test='${empty findId }'>
<form name="frmFoundNoId" method="post" action="#" enctype="UTF-8">
    <div class="wrap">
        <div class="foundNoId">
            <h2>아이디 확인</h2>
            <div class="info">
                <p>회원 정보를 찾을 수 없습니다.</p>
            </div>
            <div class="submit4_1">
                <a href="${contextPath}/main/main.do"><input type="button" id="goMain1" value="메인으로 돌아가기" style="cursor:pointer"></a>
                <a href="${contextPath}/member/findIdForm.do"><input type="button" id="gofindId" value="아이디 다시 찾기" style="cursor:pointer"></a>
            </div>
		</div>
    </div>
</form>
				
 </c:if>
</body>
</html>
