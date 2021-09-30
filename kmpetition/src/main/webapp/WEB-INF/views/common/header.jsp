<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>

<script type="text/javascript">
sessionStorage.setItem("contextpath", "${pageContext.request.contextPath}");
</script>

<c:if test='${not empty adminMessage }'>
	<script>
		window.onload = function() {
			alert("관리자 계정으로 로그인하셨습니다");
		}

	</script>
</c:if>

<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.87.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>Headers · Bootstrap v5.1</title>


    <!--폰트-->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">

    <!--bootstrap-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

   
<!-- Swal js-->
    <script src="${contextPath}/resources/js/header.js"></script>
</head>


<body>
	<div class="row">
        <div class="col-2 header">
            <div class="logo">
                <img class="img_jmt" src="${contextPath}/resources/img/logo.jpg" href="${contextPath}/main/main.do">
            </div>
        </div>

        <div class="col-10" id="cont">

            <div class="row">
                <div class="col">
                    <div class="row">
                        <div class="col cont-1">

                        </div>
                    </div>
                    <div class="row">
                        <div class="col cont-3">

                            <div class="row">

                                <div class="col-6 cont-3">
                                    <div class="search_text1">
                                        <input type="search" class="form-control search_text" id="form_text" placeholder="Search...">
                                    </div>
                                </div>


				<c:choose>
					<c:when test="${isLogOn==true and not empty memberInfo }">
					<!-- CSS -->
   					 <link rel="stylesheet" href="${contextPath}/resources/css/header_user_01.css" type="text/css">
					
						<div class="col-6 cont-3">
                                    <div class="text-end">
                                        <div>
                                            <button onclick="location.href = '${contextPath}/member/logout.do' " type="button" class="btn btn-primary">로그아웃</button>
                                            <button onclick="location.href = '${contextPath}/mypage/modMember.do' " type="button" class="btn btn-primary">마이페이지</button>
                                            
                                            <button onclick="location.href = '' " type="button" class="btn btn-primary">이용안내</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-2">
					</c:when>
					<c:when test="${isLogOn==true and isAdmin==true }">
					<!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header_ad_01.css" type="text/css">
						 <div class="col-6 cont-3">
                                    <div class="text-end">
                                        <div>
                                            <button onclick="location.href = '${contextPath}/admin/memberList.do' " type="button" class="btn btn-danger">관리자페이지</button>
                                            <button onclick="location.href = '${contextPath}/member/logout.do' " type="button" class="btn btn-primary">로그아웃</button>
                                            <button onclick="location.href = '#' " type="button" class="btn btn-primary">이용안내</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-2">
					</c:when>
					<c:otherwise>
					<!-- CSS -->
    <link rel="stylesheet" href="${contextPath}/resources/css/header_user_02.css" type="text/css">
						<div class="col-6 cont-3">
                                    <div class="text-end">
                                        <div>
                                            <button onclick="location.href = '${contextPath}/member/loginForm.do' " type="button" class="btn btn-light text-dark me-2">로그인</button>
                                            <button onclick="location.href = 'Lipage_noticle.html' " type="button" class="btn btn-primary">이용안내</button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-2">
                        
                    </div>
                    </div>
                    <div class="row">
					</c:otherwise>
				</c:choose>
				
                        <div class="col">
                            <form class="col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto">

                                <div class="layer" id="layer">

                                    <div class="alert alert-primary layer_content">
                                        <div class="month_button">
                                            <a>
                                            <a><input type="date" class="month" id="today1"></a>
                                            <a><input type="date" class="month" id="today1"></a>
                                            <li type="button" class="btn btn-secondary month" id="month1">1개월</li>
                                            <li type="button" class="btn btn-secondary month" id="month2">2개월</li>
                                            <li type="button" class="btn btn-secondary month" id="month3">3개월</li>
                                            </a>
                                        </div>

                                        <div class="checkbox_lang">
                                            <a><input type="checkbox" class="language">제외할 단어</a>
                                        </div>

                                        <div class="checkbox2_lang">
                                            <ul>
                                                <div id="boxWrap">

                                                    <input type="text" class="original">
                                                    <button type="button" class="btn btn-outline-primary button">추가</button>


                                                </div>
                                            </ul>
                                        </div>

                                        <div class="checkbox3_lang">
                                            <a><input type="checkbox" class="language">포함할 단어</a>
                                        </div>

                                        <div class="checkbox4_lang">
                                            <ul>
                                                <div id="boxWrap2">
                                                    <input type="text" class="original">
                                                    <button type="button" class="btn btn-outline-primary button2">추가</button>
                                                </div>
                                            </ul>
                                        </div>

                                        <div class="serch">
                                            <button class="btn btn btn-primary" type=" button" id="serch_serch">검색</button>
                                            <button class="btn btn-secondary" type=" button" id="layer_close">닫기</button>
                                        </div>
                                    </div>
                                </div>

                            </form>

                        </div>

                    </div>
                    
                </div>
            </div>

        </div>
    </div>
</body>
</html>