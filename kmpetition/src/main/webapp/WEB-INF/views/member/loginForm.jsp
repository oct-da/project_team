<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

    <%
    String msg = (String)request.getAttribute("msg");
    Cookie[] c = request.getCookies();
    String cookieVal = "";
    if(c != null) {
        for(Cookie i:c) {
            if(i.getName().equals("saveId")) {
                cookieVal = i.getValue();
            }
        }
    }
    
%>
<c:if test='${not empty message}'>
        <script>
            popAlert("something", "not empty message", "question", "확인");

        </script>
        <c:if test='${message == "notExist"}'>
            <script>
                var msg = "${message}";
                window.onload = function() {
                    result();
                }

                function result() {
                    popAlert("알림 메시지", "존재하지 않는 회원입니다.", "info", "확인");
                }

            </script>
        </c:if>
    </c:if>
    <%--
    <c:if test='${empty message}'>
        <script>
            window.onload = function() {
                result();
            }

            function result() {
                popAlert("알림 메시지", "메시지가 없습니다.", "info", "확인");
            }

        </script>
        
    </c:if>
     --%>
</head>
<body>
	<form name="frmLogin" method="post" action="${contextPath}/member/login.do">
        <div class="wrap">
            <div class="login">
                <h2>로그인</h2>
                <div class="login_id">
                    <input type="text" id="input_id" name="user_id" placeholder="아이디" value="<%=cookieVal !="" ? cookieVal : "" %>">
                </div>
                <div class="login_pw">
                    <input type="password" id="input_pw" name="user_pw" placeholder="비밀번호">
                </div>
                <div class="login_etc">
                    <div class="checkbox">
                        <input type="checkbox" id="checkbox" class="keepLogin" name="saveId" <%=cookieVal!=""?"checked" : ""%>>
                        <label class="keepLogin">아이디 저장</label>
                    </div>
                    <div class="forgot_id">
                        <a href="${contextPath}/member/findIdForm.do">아이디 찾기</a>
                    </div>
                    <div class="forgot_pw">
                        <a href="${contextPath}/member/findPwdForm.do">비밀번호 찾기</a>
                    </div>
                    <div class="join_new">
                        <a href="${contextPath}/member/memberForm.do">회원가입</a>
                    </div>
                </div>
                <div class="submit2">
                    <input type="button" id="loginCnfm" value="로그인하기" style="cursor:pointer" onclick="loginChk()">
                </div>
            </div>
        </div>
    </form>
</body>
</html>