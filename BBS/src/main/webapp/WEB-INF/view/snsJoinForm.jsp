<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link href="./css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

function checkName(){
	//var id = $(선택자).명령();
	var name = $("#name").val();
	if(name == "" || name.length < 2){
		$("#joinNameConfirm").css("color", "red");
		$("#joinNameConfirm").text("닉네임을 두글자 이상 작성해주세요.");
		$("#name").focus();
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'name='+name,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				//$("#joinSubmit").prop("disabled", true);//비활성화
				$("#joinNameConfirm").css("color", "red");
				$("#joinNameConfirm").text("닉네임 " + name + "는 이미 등록되어 있습니다.");
				
			}else{
				//$("#joinSubmit").prop("disabled", false);//활성화
				$("#joinNameConfirm").css("color", "blue");
				$("#joinNameConfirm").text("닉네임");
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}

function handleOnInput(e)  {
	  e.value = e.value.replace(/[^ㄱ-힣a-zA-Z0-9]/ig, '')
	}

function handleOnEmail(e)  {
	  e.value = e.value.replace(/[^a-z0-9@.]/ig, '')
	}


</script>
</head>
<body>
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>

	<form action="join.do" method="post">
		<br/> 
				<div>
					<span id="joinNameConfirm">닉네임</span>
					<br>
					<input type="text" id="name" name="name" required="required" onchange="checkName()" oninput="handleOnInput(this)">
				</div>
					<input type="hidden" name="id" value="${snsUser.email}">
					<input type="hidden" type="password" name="password" value="${snsUser.access_token }">
					<input type="hidden" name="confirmPassword" type="hidden" value="${snsUser.access_token }">
				
					<input type="hidden" name="email" value="${snsUser.email}">
				<div>
					생일
					<br>
					<input type="date" name="birth_date" required="required">
				</div>
		
	
		<input type="submit" value="가입">
	</form>
</body>
</html>