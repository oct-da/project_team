myVisitSite.jsp

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set  var="visitList"  value="${readContent.visitList}" />
<c:set  var="totVisit"  value="${readContent.selectTotVisit}" />
<c:set  var="section"  value="${readContent.section}" />
<c:set  var="pageNum"  value="${readContent.pageNum}" />
<c:set  var="startNum"  value="${readContent.startNum}" />
<c:set var="petitionPath" value="https://www1.president.go.kr/petitions" />
 <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<c:if test="${errMsg }">

</c:if>
<body>
<table border="2px">
<tr>
	<td>No</td>
	<td>제목</td>
	<td>작성일</td>
</tr>		

<c:choose>
  <c:when test="${visitList ==null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">조회된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${visitList !=null }" >
    <c:forEach  var="article" items="${visitList }" varStatus="articleNum" >
     <tr align="center">
	<td width="5%">${article.no}</td>
	<td width="40%"><a href="${petitionPath}/${article.no}" >${article.title}</a></td>
	<td width="10%">${article.viewdate }</td>
	  
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
	
	</table>
	
	
<div class="cls2">
 <c:if test="${visitList != null }" >
      <c:choose>
        <c:when test="${totVisit >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	         <c:if test="${section >1 && page==1 }">
	          <a class="no-uline" href="${contextPath }/board/boardList.do?section=${section-1}&pageNum=1">&nbsp; pre </a>
	         </c:if>
	          <a class="no-uline" href="${contextPath }/board/boardList.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
	         <c:if test="${page ==10 }">
	          <a class="no-uline" href="${contextPath }/board/boardList.do?section=${section+1}&pageNum=1">&nbsp; next</a>
	         </c:if>
	      </c:forEach>
        </c:when>
        <c:when test="${totVisit ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	        <a class="no-uline"  href="#">${page } </a>
	      </c:forEach>
        </c:when>
        
        <c:when test="${totVisit< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
	      <c:forEach   var="page" begin="1" end="${totList/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/boardList.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:when>
	          <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/boardList.do?section=${section}&pageNum=${page}">${page } </a>
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>     

</body>