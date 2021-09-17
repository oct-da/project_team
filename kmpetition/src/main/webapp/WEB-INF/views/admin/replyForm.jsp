<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" import="java.util.Date"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%=new Date()%>" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) { // [리스트로 돌아가기] 나 [취소] 눌렀을 때 obj <- this.form(폼 객체 전체)
		obj.action = "${contextPath}/board/noticeList.do";
		obj.submit();
	}

	var cnt = 1;
	function fn_addFile() {
		$("#d_file")
				.append("<br>" + "<input type='file' name='file"+cnt+"' />");
		cnt++;
	}
</script>


<body>
	<form method="post" action="${contextPath}/admin/addReply.do"
		enctype="multipart/form-data">
		<table border=0 align="center">
				<td width="150" align="center" bgcolor="#FF9933">본문 글번호</td>
				<td><input type=text name="articleNO" value="${articleNO }" /></td>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td><input type=text name="title" id="i_title" /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td><textarea rows="20" cols="60" name="content" id="i_content"></textarea></td>
			</tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">등록일자</td>
				<td><input type=text value="<fmt:formatDate value="${now}" />"
					disabled /></td>
			</tr>
			<tr>
				<td align="right">파일 첨부</td>
				<td align="left"><input type="button" value="파일 추가"
					onClick="fn_addFile()" /></td>


			</tr>
			<tr>
				<td colspan="4"><div id="d_file"></div></td>
			</tr>


			<tr id="tr_btn">
				<td><input type=button value="리스트로 돌아가기"
					onClick="backToList(this.form)"> <input type="submit"
					value="등록하기"></td>
					
			</tr>


		</table>
	</form>
</body>
