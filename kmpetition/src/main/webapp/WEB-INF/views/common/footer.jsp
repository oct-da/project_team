<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div style="padding-left: 200px; background-color: #64A0FF;">
	</br>
	<p>footer</p>
	<a href="${contextPath }/board/noticeList.do">공지사항</a>
	</br>
	<a href="${contextPath }/board/boardList.do">문의게시판</a>
	</br>
</div>

