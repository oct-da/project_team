<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<!-- 제이쿼리를 사용하기 위한 srcipt -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
/* ID중복체크 함수 */
function fn_overlapped(){
    var _id=$("#id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#btnOverlapped').prop("disabled", true);
       	    $('#id').prop("disabled", true);
       	    $('#id').val(_id);
          }else{
        	  alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	
 

/* 이메일 중복체크 */
function fn_checkEmail(){
    var _email=$("#email").val();
    if(_email==''){
   	 alert("이메일을 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/checkEmail.do",
       dataType:"text",
       data: {email:_email},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 이메일입니다.");
       	    $('#btnCheckEmail').prop("disabled", true);
       	    $('#email').prop("disabled", true);
       	    $('#email').val(_email);
          }else{
        	  alert("사용할 수 없는 이메일입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	
</script>
</head>
<body>
	<h3>회원가입 화면</h3>
	<form action="${contextPath}/member/addMember.do" method="post">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td><input type="text" name="id" id="id" size="20" /> <input
						type="button" id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비번</td>
				<td><input type="password" name="pwd" id="pwd" size="20" /></td>
				</tr>
				<tr class="dot_line">
					
				<td class="fixed_join">이름</td>
				<td><input type="text" name="name" id="name" size="20" /></td>
				</tr>
				<tr class="dot_line">
				<td class="fixed_join">폰번</td>
				<td><input type="text" name="phone" id="phone" size="20" /></td>
				</tr>
				<tr class="dot_line">
					
				<td class="fixed_join">이메일</td>
					<td>
					  <input type="text" name="email" id="email" size="20" />
					<input type="button" id="btnCheckEmail" value="중복체크" onClick="fn_checkEmail()" /></td>
				</tr>
				
				
		<table align=center>
		<tr>
			<td>
				<input type="submit" value="완료">
				<input type="reset" value="다시입력">
				<td><a href="${contextPath }/main/main.do">메인으로 이동</a></td>
			</td>
		</tr>
	</table>
</form>	

</body>
</html>