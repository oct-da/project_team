<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>회원가입</title>

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
	<form name="frmJoin" method="post" action="${contextPath}/member/addMember.do">
        <div class="wrap">
            <div class="join">
                <h2>회원가입</h2>
                <div class="join_id">
                    <input type="text" id="input_id" name="user_id" placeholder="아이디(영문+숫자 6~16자)">
                    <input type="button" id="idCheckBtn" value="중복확인" style="cursor: pointer" onclick="idCheck(this.form)">
                    <input type="hidden" name="checkedId" value="">
                </div>
                <div class="join_pw">
                    <input type="password" id="input_pw" name="user_pw" placeholder="비밀번호(영문+숫자 8~16자)">
                </div>
                <div class="join_pw_cnfm">
                    <input type="password" id="input_pw_re" name="user_pw_re" placeholder="비밀번호 확인">
                </div>
                <div class="join_name">
                    <input type="text" id="input_name" name="user_name" placeholder="이름">
                </div>
                <div class="join_email">
                    <input type="text" id="input_email" name="user_email" placeholder="이메일">
                    <input type="button" id="emailCheckBtn" value="중복확인" style="cursor: pointer" onclick="emailCheck()">
                    <input type="hidden" name="checkedEmail" value="">
                </div>
                <div class="join_num">
                    <input type="text" id="input_num" name="user_num" placeholder="전화번호">
                </div>
                <div class="join_etc">
                    <div class="checkbox">
                        <input type="checkbox" id="chkbox1">&nbsp;<a href="#">개인정보 취급 및 수집 동의</a>
                    </div>
                    <div class="checkbox">
                        <input type="checkbox" id="chkbox2">&nbsp;<a href="#">이용 약관 동의</a>
                    </div>
                </div>
                <div class="submit1">
                    <input type="button" id="joinCnfm" value="가입하기" style="cursor:pointer" onclick="joinChk()">
                </div>
            </div>
        </div>
    </form>
</body>
</html>