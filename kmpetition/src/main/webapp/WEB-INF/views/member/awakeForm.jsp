<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>휴면 해제</title>

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" type="text/css">

    <!-- jQuery, js -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    
    <!-- Swal js-->
    <script src="${contextPath}/resources/js/swalModal.js"></script>

    <!-- alert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

    <!-- Include the Bootstrap 4 theme -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4/bootstrap-4.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2/dist/sweetalert2.min.js"></script>
</head>

<body>
    <form name="frmAwakeId" method="post" action="${contextPath}/member/awakeMember.do">
        <div class="wrap">
            <div class="awakeId">
                <h2>휴면 해제하기</h2>
                <div class="awake_id">
                    <input type="text" id="input_id" name="user_id" placeholder="아이디">
                </div>
                <div class="awake_pw">
                    <input type="password" id="input_pw" name="user_pw" placeholder="비밀번호">
                </div>
                <div class="awake_name">
                    <input type="text" id="input_name" name="user_name" placeholder="이름">
                </div>
                <div class="awake_email">
                    <input type="text" id="input_email" name="user_email" placeholder="이메일">
                </div>
                <div class="submit9">
                    <a href="${contextPath}/main/main.do"><input type="button" id="goMain" value="메인으로 돌아가기" style="cursor:pointer"></a>
                    <a href="#"><input type="button" id="awakeCnfm" value="확인" style="cursor:pointer" onclick="awakeChk()"></a>
                </div>
            </div>
        </div>
    </form>
</body>

</html>