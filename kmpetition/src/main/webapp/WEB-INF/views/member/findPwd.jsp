<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" type="text/css">

    <!-- jQuery, js -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    
    
<c:if test='${not empty result }'>
	<!-- Swal js-->
    <script src="${contextPath}/resources/js/swalModal.js"></script>
    <!-- alert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.all.min.js"></script>

    <!-- Include the Bootstrap 4 theme -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@sweetalert2/theme-bootstrap-4/bootstrap-4.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2/dist/sweetalert2.min.js"></script>
</c:if>  
    
</head>

<body>
<c:if test='${empty result }'>
	<form name="frmFoundNoPw" method="post" action="#" enctype="UTF-8">
        <div class="wrap">
            <div class="foundNoPw">
                <h2>비밀번호 확인</h2>
                <div class="info">
                    <p>회원 정보를 찾을 수 없습니다.</p>
                </div>
                <div class="submit5_1">
                    <a href="${contextPath}/main/main.do"><input type="button" id="goMain2" value="메인으로 돌아가기" style="cursor:pointer"></a>
                    <a href="${contextPath}/member/findPwdForm.do"><input type="button" id="gofindPwd" value="비밀번호 다시 찾기" style="cursor:pointer"></a>
                </div>
            </div>
        </div>
    </form>
</c:if>
		
<c:if test='${not empty result }'>
<form name="frmChangePw" method="post" action="${contextPath}/member/modPwd.do" enctype="UTF-8">
        <div class="wrap">
            <div class="changePw">
                <h2>비밀번호 변경</h2>
                <input type="hidden" name="id" value="${id}" size="20" />
                
                <div class="new_pw">
                    <input type="password" id="input_pw" name="user_pw" placeholder="새로운 비밀번호">
                </div>
                <div class="new_pw_re">
                    <input type="password" id="input_pw_re" name="user_pw_re" placeholder="비밀번호 확인">
                </div>
                <div class="submit6">
                    <input type="button" id="findCnfm" value="확인" style="cursor:pointer" onclick="pwChk()">
                </div>
            </div>
        </div>
    </form>
</c:if>
		
	</br> </br>
	</P>
	<td><a href="${contextPath }/member/memberForm.do">회원가입창으로 이동</a></td>
</body>
</html>
