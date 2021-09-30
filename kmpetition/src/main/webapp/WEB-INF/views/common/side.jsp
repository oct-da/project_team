<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>사용자 메뉴</title>

    <!-- 구글 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/Menu_bar.css">
    <!-- Swal js-->
    <script src="${contextPath}/resources/js/Menu_bar_Script.js"></script>
    <!-- Bootstrap -->	
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

</head>
<body>

    <ul id="menu">
        <li class="group">
            <div class="title">그래프 상세보기</div>
            <ul class="sub">
                <li><a href="List_Circlepart.html">분야별 현황</a></li>
                <li><a href="List_Network.html">네트워크 그래프</a></li>
                <li><a href="List_Time.html">시계열 그래프</a></li>
            </ul>
        </li>
        <li class="group">
            <div class="title">분석 리포트</div>
            <ul class="sub">
                <li><a href="List_weekly.html">주간 동향</a></li>
                <li><a href="List_monthly.html">월간 동향</a></li>
            </ul>
        </li>
        <li class="group">
            
            <div OnClick="location.href ='#'" style="cursor:pointer;" class="title">공지사항</div>


        </li>
        <li class="group">
            
            <div OnClick="location.href ='#'" style="cursor:pointer;" class="title">1:1문의</div>
        </li>
    </ul>

</body>
		</html>