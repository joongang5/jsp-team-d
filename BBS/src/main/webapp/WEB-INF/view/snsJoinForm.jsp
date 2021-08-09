<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link href="./css/joinForm.css" rel="stylesheet">
<link href="./css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

function checkName(){
	var name = $("#name").val();
	if(name == ""){
		$("#joinNameConfirm").css("color", "red");
		$("#joinNameConfirm").text("닉네임을 작성해주세요.");
		$("#name").focus();
		$("#joinSubmit").prop("disabled", true);
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'name='+name,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#joinNameConfirm").css("color", "red");
				$("#joinNameConfirm").text("닉네임 " + name + "는 이미 등록되어 있습니다.");
				$("#joinSubmit").prop("disabled", true);
			}else{
				$("#joinNameConfirm").css("color", "blue");
				$("#joinNameConfirm").text("닉네임");
				$("#joinSubmit").prop("disabled", false);
				return true;
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}

function checkBirth() {
	var birth = $("#birth_date").val();
	var bc = document.getElementById('birthConfirm');
    
	if(birth.include('-') != 2 && birth.length() != 10){
    	bc.innerHTML='생년월일을 다시 확인해주세요.';
        bc.style.color='red';
        $("#joinSubmit").prop("disabled", true);
        return false;
    } else {
    	$("#joinSubmit").prop("disabled", false);
    	$("#joinSubmit").css("color", "black");
    	bc.innerHTML='생일';
        bc.style.color='blue';
        return true;
    }
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
					<span id="joinNameConfirm">닉네임</span><span id="joinNameInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
					<br><input type="text" id="name" name="name" required="required" onchange="checkName()" oninput="handleOnInput(this)">
				</div>
					<input type="hidden" name="id" value="${snsUser.email}">
					<input type="hidden" type="password" name="password" value="${snsUser.access_token }">
					<input type="hidden" name="confirmPassword" type="hidden" value="${snsUser.access_token }">
				
					<input type="hidden" name="email" value="${snsUser.email}">
				<div>
					<span id="birthConfirm">생일</span><span id="joinBirthInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
					<br><input type="date" id="birth_date" name="birth_date" value="2000-01-01" required="required" onchange="checkBirth()">
				</div>
		
	
		<input type="submit" value="가입">
	</form>
</body>
</html>