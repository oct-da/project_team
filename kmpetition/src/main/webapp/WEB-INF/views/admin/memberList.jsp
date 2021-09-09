<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set  var="memberList"  value="${memberMap.memberList}" />
<c:set  var="totArticles"  value="${memberMap.totMember}" />
<c:set  var="section"  value="${memberMap.section}" />
<c:set  var="pageNum"  value="${memberMap.pageNum}" />
<c:set  var="startNum"  value="${memberMap.startNum}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="2px">
<tr>
	<td>No</td>
	<td>아이디</td>
	<td>이름</td>
	<td>이메일</td>
	<td>전화번호</td>
	<td>최종접속일</td>
</tr>		


<c:choose>
  <c:when test="${memberList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${memberList !=null }" >
    <c:forEach  var="article" items="${memberList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${startNum-1+articleNum.count}</td>
	<td width="10%">${article.id }</td>
	<td width="10%">${article.name }</td>
	<td width="10%">${article.email }</td>
	<td width="10%">${article.phone }</td>
	<td width="10%">${article.lastlogin }</td>
	  
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
	
	</table>
	
	
<div class="cls2">
 <c:if test="${totArticles != null }" >
      <c:choose>
        <c:when test="${totArticles >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	         <c:if test="${section >1 && page==1 }">
	          <a class="no-uline" href="${contextPath }/admin/memberList.do?section=${section-1}&pageNum=1">&nbsp; pre </a>
	         </c:if>
	          <a class="no-uline" href="${contextPath }/admin/memberList.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
	         <c:if test="${page ==10 }">
	          <a class="no-uline" href="${contextPath }/admin/memberList.do?section=${section+1}&pageNum=1">&nbsp; next</a>
	         </c:if>
	      </c:forEach>
        </c:when>
        <c:when test="${totArticles ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	        <a class="no-uline"  href="#">${page } </a>
	      </c:forEach>
        </c:when>
        
        <c:when test="${totArticles< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
	      <c:forEach   var="page" begin="1" end="${totArticles/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:when>
	          <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>    
	
</body>
</html>