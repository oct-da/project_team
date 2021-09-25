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
     
     /* 파일 다운로드 함수 */
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
     
     /*  답글쓰기 버튼 클릭 */ 
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
     
     /* (본문)수정하기 버튼 클릭 */
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
     
     /* (답글)수정하기 버튼 클릭 */
     function modReply(obj) {
     	var action="${contextPath}/admin/modReplyForm.do";
     	/* 
  		var form = document.createElement("form");
   	  	form.setAttribute("method", "post"); */
    	obj.setAttribute("action", action);
    	 	 
    	var noInput = document.createElement("input");
    	noInput.setAttribute("type","hidden");
    	noInput.setAttribute("name","articleNO");
    	noInput.setAttribute("value", "${boardVO.articleNO}");
    		
    	obj.appendChild(noInput);
    	/* document.body.appendChild(form); */
    	    
    	obj.submit();
 		
 	}

     /* (본문)삭제하기 버튼 클릭 */
     function fn_delete(obj, articleNO) {
    	var action="${contextPath}/board/removeBoard.do?articleNO="+articleNO;
    	location.href=action;
	}

     /* (답글)삭제하기 버튼 클릭 */
     function fn_replyDelete(obj, articleNO) {
    	var action="${contextPath}/admin/removeReply.do?articleNO="+articleNO;
    	/* location.href=action; */
    	
    	$.ajax({
    		type : "GET",
    		dataType:"text",
    		data:{
    			articleNO:articleNO,
    				},
    		url:action,
    		async: false,
    		success : function(data, textStatus) {
    			if (data.trim()=='success'){
    				alert('삭제했습니다.');
    				location.reload();
    			}
    			if(data.trim()=='error'){
    				alert('오류가 발생했습니다. \n다시 시도해주세요.');
    			}
    		}
    	});
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
					<input type="button" value="파일다운로드" onClick="fileDownload('${contextPath }/downloadFile.do','${file.uploadfile }')"/>
					</br> 
				</c:forEach>
				</td>
				</tr>
			</c:if>
			<tr id="tr_btn">
				<td colspan="2" align="center">
			</tr>
			<tr>
			<td><input type=button value="리스트로 돌아가기" onClick="backToList(this.form)"></td>
			<td>
				<input type=button value="수정하기" onClick="modArticle('${boardVO.articleNO}')">
				<input type=button value="삭제하기버튼" onClick="fn_delete(this.form, '${boardVO.articleNO}')">
				<a href="${contextPath }/board/removeBoard.do?articleNO=${boardVO.articleNO}" role="button">삭제a</a>
				<input type=button value="답글쓰기"  onClick="fn_replyForm('${boardVO.articleNO }')" /><td>
			</td>
			</tr>
			<tr></tr>
			
			</table>
	</form>
	
			<!-- 답글 부분 -->
	<c:if test="${not empty replyVO }">
	<form name="frmReply" method="post" action="${contextPath}"
		enctype="multipart/form-data">
		<table border=0 align="center">
			
			
			<tr>
				<td colspan='2' width="150" align="center" bgcolor="yellowgreen">답글</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="yellowgreen">제목</td>
				<td><input type=text  name="title" value="${replyVO.title}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="yellowgreen">내용</td>
				<td><input type=text name="content" value="${replyVO.content}" readonly="readonly" />
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
				<td>
					<input type=button value="수정하기" onClick="modReply(this.form)">
					<input type=button value="답글삭제" onClick="fn_replyDelete(this.form, '${boardVO.articleNO}')"></td>
				</tr>
			</c:if>
			
		</table>
	</form>
</body>
