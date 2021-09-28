<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>회원 정보 수정</title>

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" type="text/css">

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

    <form name="frmModMember" method="post" action="${contextPath}/mypage/modMember.do">
        <div class="wrap">
            <div class="modMember">
                <h2>회원 정보 수정</h2>
                <div class="mod_id">
                    <input type="text" id="input_id" name="id" value="${memberInfo.id}" readonly="readonly">
                </div>
                <div class="mod_pw">
                    <input type="password" id="input_pw" name="pwd" value="${memberInfo.pwd}" placeholder="비밀번호(영문+숫자 8~16자)">
                </div>
                <div class="mod_pw_cnfm">
                    <input type="password" id="input_pw_re" name="user_pw_re" value="${memberInfo.pwd}" placeholder="비밀번호 확인">
                </div>
                <div class="mod_name">
                    <input type="text" id="input_name" name="name" value="${memberInfo.name}" placeholder="이름">
                </div>
                <div class="mod_email">
                    <input type="text" id="input_email" name="email" value="${memberInfo.email}" placeholder="이메일">
                    <input type="button" id="emailCheckBtn" value="중복확인" style="cursor: pointer" onclick="emailCheck()">
                    <input type="hidden" name="checkedEmail" value="">
                </div>
                <div class="mod_num">
                    <input type="text" oninput="this.value=this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="11" id="input_num" name="user_num" value="${memberInfo.phone}" placeholder="전화번호">
                </div>
                <div class="submit10">
                    <input type="button" id="modCnfm" value="수정하기" style="cursor:pointer" onclick="modCheck()">
                </div>
            </div>
        </div>
    </form>

</body>
</html>