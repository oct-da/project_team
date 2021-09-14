<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" import="java.util.Date"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="now" value="<%=new Date()%>" />

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	
/* 	name이 같은 input은 
	request.getParameterValues('이름');
	으로 한 번에 받아올 수 있음 */
	function fn_addSearchWord(){
		$("#d_search")
		.append("<br>" + "<input type='text' name='searchWord' />");
		cntSeacrch++;
	}
	
	function fn_addExceptWord(){
		$("#d_except")
		.append("<br>" + "<input type='text' name='exceptWord' />");
		cntExcept++;
	}
</script>


<body>
	<form method="post" action="${contextPath }/board/search.do">
		<table border=0 align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">포함할 워드</td>
				<td><input type=text name="searchWord" />
				<td align="left"><input type="button" value="워드 추가"
					onClick="fn_addSearchWord()" /></td>
			</tr>
			<tr>
				<td colspan="4"><div id="d_search"></div></td>
			</tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제외할 워드</td>
				<td><input type=text name="exceptWord"  /></td>
				<td align="left"><input type="button" value="워드 추가"
					onClick="fn_addExceptWord()" /></td>
			</tr>
			<tr>
				<td colspan="4"><div id="d_except"></div></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">시작기간</td>
				<td><input type=text  name="startDate"/></td>
			</tr>

			<tr>
				<td width="150" align="center" bgcolor="#FF9933">종료기간</td>
				<td><input type=text name="endDate" /></td>
			</tr>
			<tr>
				<td colspan='2' width="150" align="center" bgcolor="#FF9933">기간은 yyyy-MM-dd 형태로 적어주세요</td>
			</tr>
			


			<tr id="tr_btn">
				<td><input type="submit" value="검색"></td>
					
			</tr>


		</table>
	</form>
</body>
