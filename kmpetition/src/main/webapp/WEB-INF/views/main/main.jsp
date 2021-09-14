<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	var cnt = 1;
	function fn_addFile() {
		$("#d_file").append(
				"<br>" + "<input type='file' name='file" + cnt + "' />");
		cnt++;
	}
	
	function fn_download() {
		window.location ='${contextPath}/downloadFile.do?fileName=sample.txt';
	}
</script>

</head>
<body>
	<h1>views/main/main.jsp</h1>

	<P>
		MainController에서 동작합니다.


		<!-- 파일 업로드 테스트 -->
		<c:if test="${isLogOn }">
			<h1>파일 업로드 하기</h1>
			<form method="post" action="${contextPath}/member/upload"
				enctype="multipart/form-data">
				<input type="button" value="파일 추가" onClick="fn_addFile()"><br>

				<div id="d_file"></div>
				<input type="submit" value="업로드">
			</form>

		</c:if>
		
		</br>
		</br>
		</br>
		
		 <input type="button" name="download" value="파일다운로드 버튼" onclick="fn_download()">
		 <a href="${contextPath}/downloadFile.do?fileName=sample.txt">파일다운 링크</a> 
	</P>
</body>
</html>
