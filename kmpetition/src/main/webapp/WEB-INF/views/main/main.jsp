<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
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

	</P>
	
	
		<div class="chart" id="graph">
        <script>
        var graphs = ${json}

        Plotly.plot('graph', graphs, {});
        </script>
    </div>
    
</body>
</html>
