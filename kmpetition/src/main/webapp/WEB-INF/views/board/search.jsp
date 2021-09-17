<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="petitionPath" value="https://www1.president.go.kr/petitions" />
<c:set var="petitionList" value="${petitionMap.petitionList }" />
<c:set var="totSearchList" value="${petitionMap.totSearchList }" />
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 

<script type="text/javascript">

var id="${memberInfo.id}";

/* 제목링크 클릭시 DB에 방문기록 저장 후 새창 열기로 해당 청원페이지로 이동 */
function saveVisit(NO, title){
	alert('함수 진입');
	
	var action="${contextPath}/member/saveVisit.do";
	var petitionURL="${petitionPath }/NO";
	
	
	$.ajax({
		type : "post",
		dataType:"text",
		data:{
			url:petitionURL,
			id:id,
			title:title
				},
		url:action,
		async: false,
		success : function(data, textStatus) {
			if (data.trim()=='success'){
				alert('성공');
			}
			window.open(petitionURL);
		}
	});
    
}

function openSite(url) {
	window.open(url);
}
</script>

<body>
<table border="2px">
<tr>
   <td>글번호</td>
   <td>제목</td>
   <td>청원시작일</td>
</tr>      

<c:choose>
  <c:when test="${petitionList ==null || totSearchList == null}" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">조회된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${petitionList !=null }" >
    <c:forEach  var="article" items="${petitionList }" varStatus="articleNum" >
     <tr align="center">
   <td width="5%">${article.NO}</td>
   <td width="40%"><a  href="javascript:saveVisit('${article.NO}','${article.title }');" >${article.title }</a></td>
   <td width="10%">${article.subdate }</td>
     
   </tr>
    </c:forEach>
     </c:when>
    </c:choose>
   
   </table>
   
   
<div class="cls2">
 <c:if test="${totSearchList != null }" >
      <c:choose>
        <c:when test="${totSearchList >100 }">  <!-- 글 개수가 100 초과인경우 -->
         <c:forEach   var="page" begin="1" end="10" step="1" >
            <c:if test="${section >1 && page==1 }">
             <a class="no-uline" href="${contextPath }/board/search.do?section=${section-1}&pageNum=1">&nbsp; pre </a>
            </c:if>
             <a class="no-uline" href="${contextPath }/board/search.do?section=${section}&pageNum=${page}">${(section-1)*10 +page } </a>
            <c:if test="${page ==10 }">
             <a class="no-uline" href="${contextPath }/board/search.do?section=${section+1}&pageNum=1">&nbsp; next</a>
            </c:if>
         </c:forEach>
        </c:when>
        <c:when test="${totSearchList ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
         <c:forEach   var="page" begin="1" end="10" step="1" >
           <a class="no-uline"  href="#">${page } </a>
         </c:forEach>
        </c:when>
        
        <c:when test="${totSearchList< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
         <c:forEach   var="page" begin="1" end="${totSearchList/10 +1}" step="1" >
            <c:choose>
              <c:when test="${page==pageNum }">
               <a class="sel-page"  href="${contextPath }/board/search.do?section=${section}&pageNum=${page}">${page } </a>
             </c:when>
             <c:otherwise>
               <a class="no-uline"  href="${contextPath }/board/search.do?section=${section}&pageNum=${page}">${page } </a>
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
</body>