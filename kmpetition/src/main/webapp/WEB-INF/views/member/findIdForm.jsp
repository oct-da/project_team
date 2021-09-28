<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>아이디 찾기</title>

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">

    <!-- jQuery, js -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <!-- Swal js-->
    <script src="${contextPath}/resources/js/swalModal.js"></script>

    <!-- alert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.all.min.js"></script>

    <!-- Include the Bootstrap 4 theme -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4/bootstrap-4.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2/dist/sweetalert2.min.js"></script>
</head>

<body>
    <form name="frmFindId" method="post" action="${contextPath}/member/findId.do">
        <div class="wrap">
            <div class="findId">
                <h2>아이디 찾기</h2>
                <div class="find_name1">
                    <input type="text" id="input_name" name="user_name" placeholder="가입 시 입력한 이름">
                </div>
                <div class="find_email1">
                    <input type="text" id="input_email" name="user_email" placeholder="가입 시 입력한 이메일">
                </div>
                <div class="submit3">
                    <a href="${contextPath}/main/main.do"><input type="button" id="goMain" value="메인으로 돌아가기" style="cursor:pointer"></a>
                    <input type="button" id="findIdCnfm" value="확인" style="cursor:pointer" onclick="findIdChk()">
                </div>
            </div>
        </div>
    </form>
</body>

</html>