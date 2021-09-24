<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="boardVO" value="${articleMap.boardVO}" />
<c:set var="uploadList" value="${articleMap.uploadList}" />
<c:set var="replyVO" value="${articleMap.replyVO}" />
<c:set var="replyUploadList" value="${articleMap.replyUploadList}" />

 <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >

     function backToList(obj){   // [리스트로 돌아가기] 나 [취소] 눌렀을 때 obj <- this.form(폼 객체 전체) / frmArticle
	    obj.action="${contextPath}/board/boardList.do";
	    obj.submit();
     }
     
     function fileDownload(url,fileName){
    	 var form = document.createElement("form");
    	 form.setAttribute("method", "post");
    	 form.setAttribute("action", url);
    	 
    	 var fileInput = document.createElement("input");
    	 fileInput.setAttribute("type","hidden");
    	 fileInput.setAttribute("name","fileName");
    	 fileInput.setAttribute("value", fileName);
	     
	     form.appendChild(fileInput);
	     document.body.appendChild(form);
	     form.submit();
     }
     
     function fn_replyForm(articleNO) {
		var action="${contextPath}/admin/replyForm.do";
		var form = document.createElement("form");
 	  	form.setAttribute("method", "post");
  	    form.setAttribute("action", action);
  	 	 
  	 	var noInput = document.createElement("input");
  	 	noInput.setAttribute("type","hidden");
  	 	noInput.setAttribute("name","articleNO");
  	 	noInput.setAttribute("value", articleNO);
  		
  	    form.appendChild(noInput);
  	    document.body.appendChild(form);
  	    
  	    form.submit();
	}
     
     function modArticle(articleNO) {
    	var action="${contextPath}/board/modArticleForm.do";
    	
 		var form = document.createElement("form");
  	  	form.setAttribute("method", "post");
   	    form.setAttribute("action", action);
   	 	 
   	 	var noInput = document.createElement("input");
   	 	noInput.setAttribute("type","hidden");
   	 	noInput.setAttribute("name","articleNO");
   	 	noInput.setAttribute("value", articleNO);
   		
   	    form.appendChild(noInput);
   	    document.body.appendChild(form);
   	    
   	    form.submit();
		
	}

     function fn_delete(obj) {
    	var articleNO="${noticeVO.articleNO}";
    	var action="${contextPath}/admin/deleteNotice.do?articleNO="+articleNO;
    	obj.action=action;
     	
    	obj.submit();
	}
     
  </script>
<body>
	<form name="frmboardVO" method="post" action="${contextPath}"
		enctype="multipart/form-data">
		<table border=0 align="center">
			<tr>
				<td width=150 align="center" bgcolor=#FF9933>글번호</td>
				<td><input type="text" value="${boardVO.articleNO }" disabled />
					<input type="hidden" name="noticeNO" value="${boardVO.articleNO}" />
				</td>
			</tr>
			<tr>
			<td width=150 align="center" bgcolor=#FF9933>작성자</td>
			<td><input type="text" value="${boardVO.id }" disabled />
					
				</td>
			</tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td><input type=text value="${boardVO.title }" name="title"
					id="i_title" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td><textarea rows="20" cols="60" name="content" id="i_content"
						disabled />${boardVO.content }</textarea></td>
			</tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">등록일자</td>
				<td><input type=text
					value="<fmt:formatDate value="${boardVO.createdate}" />" disabled />
				</td>
			</tr>
			<c:if test="${not empty uploadList}">
				<tr>
				<td width="150" align="center" bgcolor="#FF9933">첨부파일</td>
				<td>
				<c:forEach var="file" items="${uploadList }">
					${file.uploadfile }		
					<input type="button" value="파일다운로드" onClick="fileDownload('${contextPath }/downloadFile.do','${file.uploadfile }')"/></br>  
					
					
				</c:forEach>
				</td>
				</tr>
			</c:if>
			<tr id="tr_btn">
				<td colspan="2" align="center">
			</tr>
			
			<c:if test="${not empty replyVO }">
			<tr>
				<td colspan='2' width="150" align="center" bgcolor="yellowgreen">답글</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="yellowgreen">제목</td>
				<td><input type=text  value="${replyVO.title}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="yellowgreen">내용</td>
				<td><input type=text value="${replyVO.content}" readonly="readonly" />
				</td>
			</tr>
			<c:if test="${not empty uploadList}">
				<tr>
				<td width="150" align="center" bgcolor="yellowgreen">첨부파일</td>
				<td>
				<c:forEach var="file" items="${replyUploadList }">
					${file.uploadfile }		
					<input type="button" value="파일다운로드" onClick="fileDownload('${contextPath }/downloadFile.do','${file.uploadfile }')"/></br>  
					
					
				</c:forEach>
				</td>
				</tr>
			</c:if>
					</tr>
			
			<tr>
			</c:if>
			<td><input type=button value="리스트로 돌아가기"
					onClick="backToList(this.form)"></td>
					<td><input type=button value="수정하기"
					onClick="modArticle('${boardVO.articleNO}')"></td>
					<input type=button value="삭제하기" onClick="fn_delete(this.form)">  
					<input type=button value="답글쓰기"  onClick="fn_replyForm('${boardVO.articleNO }')" />
				</td>
					</tr>
		</table>
	</form>
</body>
