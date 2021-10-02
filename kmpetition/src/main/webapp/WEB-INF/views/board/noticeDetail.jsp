<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="noticeVO" value="${noticeMap.noticeVO}" />
<c:set var="uploadList" value="${noticeMap.uploadList}" />
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
   content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.87.0">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>관리자 공지사항</title>

<link rel="canonical"
   href="https://getbootstrap.com/docs/5.1/examples/headers/">

<link
   href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
   rel="stylesheet">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
   integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
   crossorigin="anonymous">
<!-- noti js-->
<script src="${contextPath}/resources/js/notice.js"></script>
<!-- CSS -->
<link rel="stylesheet"
   href="${contextPath}/resources/css/AD_Notice_2.css" type="text/css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function backToList(obj) { // [리스트로 돌아가기] 나 [취소] 눌렀을 때 obj <- this.form(폼 객체 전체) / frmArticle
      obj.action = "${contextPath}/board/noticeList.do";
      obj.setAttribute("method", "get");
      obj.submit();
   }

   function modArticle(articleNO) {
      var action = "${contextPath}/admin/modNoticeForm.do";

      var form = document.createElement("form");
      form.setAttribute("method", "post");
      form.setAttribute("action", action);

      var noInput = document.createElement("input");
      noInput.setAttribute("type", "hidden");
      noInput.setAttribute("name", "articleNO");
      noInput.setAttribute("value", articleNO);

      form.appendChild(noInput);
      document.body.appendChild(form);

      form.submit();

   }

   /* 파일 다운로드 함수 */
   function fileDownload(url, fileName) {
      var form = document.createElement("form");
      form.setAttribute("method", "post");
      form.setAttribute("action", url);

      var fileInput = document.createElement("input");
      fileInput.setAttribute("type", "hidden");
      fileInput.setAttribute("name", "fileName");
      fileInput.setAttribute("value", fileName);

      form.appendChild(fileInput);
      document.body.appendChild(form);
      form.submit();
   }

   function fn_delete(obj) {
      var articleNO = "${noticeVO.articleNO}";
      var action = "${contextPath}/admin/removeNotice.do?articleNO="
            + articleNO;
      obj.action = action;

      obj.submit();
   }
</script>
</head>

<body>
   <form method="post" action="${contextPath}/admin/addNotice.do"
      enctype="multipart/form-data" name="frmNotice">
      <table border=0 align="center">
         <div class="row">

            <div class="col-8" id="cont">

               <div class="g_header">

                  <h1>공지 사항</h1>
               </div>

               <div class="row ">

                  <div class="col-10 alert alert-primary layer_content at_list">

                     <div class="row">
                        <div class="col-12 little_header">
                           <h1>${noticeVO.title }</h1>

                        </div>

                     </div>
                     <div class="row">
                        <div class="col-12 little_name">
                           <h5>글번호 : ${noticeVO.articleNO } | 작성일자 :
                              ${noticeVO.createdate }</h5>
                           <hr align="left"
                              style="border: solid 1px #0670D9; width: 100%;">
                        </div>


                     </div>
                     <div class="row">
                        <div class="col-12 text_filed">
                           <textarea class="inside_text" id="story" name="story" style="resize:none;">${noticeVO.content }</textarea>
                        </div>
                        
                     </div>

                     <c:if test="${not empty uploadList}">
                        <c:forEach var="file" items="${uploadList }">
               ${file.uploadfile }      
               <div class="row bottom_download">
                              <div class="col-12 a3-1">
                                 <button type="button" class=" btn btn-primary a3"
                                    onClick="fileDownload('${contextPath }/downloadFile.do','${file.uploadfile }')">첨부파일
                                    다운</button>
                              </div>
                           </div>
                        </c:forEach>
                     </c:if>

                     <div class="row bottom_button">
                        <div class="col-2">
                           <button type="button" class=" btn btn-primary a1"
                              onClick="backToList(this.form)">목록</button>
                        </div>
                        <div class="col-2">
                           <button type="button" class=" btn btn-secondary a2"
                              onClick="modArticle('${noticeVO.articleNO}')">수정</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         

         <%-- 첨부파일 다운로드

      <c:if test="${not empty uploadList}">
      <tr>
         <td width="150" align="center" bgcolor="#FF9933">첨부파일</td>
         <td><c:forEach var="file" items="${uploadList }">
               ${file.uploadfile }      
               <input type="button" value="파일다운로드"
                  onClick="fileDownload('${contextPath }/downloadFile.do','${file.uploadfile }')" />
               </br>
            </c:forEach></td>
      </tr>
   </c:if> --%>
      </table>
   </form>
</body>