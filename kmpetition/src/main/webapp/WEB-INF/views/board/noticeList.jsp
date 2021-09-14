<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set  var="noticeList"  value="${noticeMap.noticeList}" />
<c:set  var="totNotice"  value="${noticeMap.totNotice}" />
<c:set  var="section"  value="${noticeMap.section}" />
<c:set  var="pageNum"  value="${noticeMap.pageNum}" />
<c:set  var="startNum"  value="${noticeMap.startNum}" />

<body>
<table border="2px">
<tr>
	<td>No</td>
	<td>제목</td>
	<td>작성일</td>
</tr>		

<c:choose>
  <c:when test="${noticeList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${noticeList !=null }" >
    <c:forEach  var="article" items="${noticeList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${startNum-1+articleNum.count}</td>
	<td width="40%"><a href="${contextPath }/board/noticeDetail.do?articleNO=${article.articleNO}" >${article.title }</a></td>
	<td width="10%">${article.createdate }</td>
	  
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
	
	</table>
	
	
<div class="cls2">
 <c:if test="${totNotice != null }" >
      <c:choose>
        <c:when test="${totNotice >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	         <c:if test="${section >1 && page==1 }">
	          <a class="no-uline" href="${contextPath }/board/noticeList.do?section=${section-1}&pageNum=1">&nbsp; pre </a>
	         </c:if>
	          <a class="no-uline" href="${contextPath }/board/noticeList.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
	         <c:if test="${page ==10 }">
	          <a class="no-uline" href="${contextPath }/board/noticeList.do?section=${section+1}&pageNum=1">&nbsp; next</a>
	         </c:if>
	      </c:forEach>
        </c:when>
        <c:when test="${totNotice ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	        <a class="no-uline"  href="#">${page } </a>
	      </c:forEach>
        </c:when>
        
        <c:when test="${totNotice< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
	      <c:forEach   var="page" begin="1" end="${totNotice/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/noticeList.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:when>
	          <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/noticeList.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>     
<!--  왜인지 c 안에 작성한 코드가 반영이 안 돼서 일단 관리자검사 생략
<div>
<c:if test="${isAdmin } ">
	<a href="${contextPath }/admin/noticeForm" > 글쓰기 </a>
	</c:if>
	
<c:if test="${not isAdmin } ">
	<p>관리자가 아니므로 글쓰기 불가</p>
	</c:if>
	
</div>  
 -->
 <a href="${contextPath }/admin/noticeForm.do" > 글쓰기 </a>
	
</body>