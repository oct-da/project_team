<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
	
	<c:choose>
	
	
    <c:when test="${user_side==true}">
    <script type="text/javascript">
    	console.log('user_side가 true고 memberInfo 존재');
    </script>
    <ul id="menu">
        <li class="group">
            
            <div OnClick="location.href ='${contextPath}/mypage/myVisitSite.do'" style="cursor:pointer;" class="title">내가 열람한 정보</div>
        </li>
        
           <li class="group">
            
            <div OnClick="location.href ='${contextPath}/mypage/myList.do'" style="cursor:pointer;" class="title">내가 작성한 문의</div>
        </li>
        
           <li class="group">
            
            <div OnClick="location.href ='${contextPath}/mypage/modMemberForm.do'" style="cursor:pointer;" class="title">회원정보수정</div>
        </li>
        
         
    </ul>
    
    </c:when>
    <c:otherwise>
    <script type="text/javascript">
    	console.log('user_side가 true가 아니거나 memberInfo가 존재하지 않음');
    </script>
    
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
            
            <div OnClick="location.href ='${contextPath}/board/noticeList.do'" style="cursor:pointer;" class="title">공지사항</div>


        </li>
        <li class="group">
            
            <div OnClick="location.href ='${contextPath}/board/boardList.do'" style="cursor:pointer;" class="title">1:1문의</div>
        </li>
    </ul>
    </c:otherwise>

</c:choose>
</body>
		</html>