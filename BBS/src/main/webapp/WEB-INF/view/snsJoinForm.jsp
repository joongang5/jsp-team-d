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
<style type="text/css">

#joinbox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
}

#text_align {
	text-align: center;
}

#joinMsg {
	font-size: 12px;
}

#joinErr {
	font-size: 11px;
}

.join_input {
	border: none;
	color: white;
	border-bottom: 2px solid #D1D1D4;
	background: #212121;
	padding: 15px;
	padding-left: 24px;
	margin-left: 18px;
	font-weight: 700;
	width: 75%;
	transition: .4s;
}

.join_input:active,
.join_input:focus,
.join_input:hover {
	background: #212121;
	outline: none;
	border-bottom-color: #6A679E;
}

#joinSubmit {
	background: #fff;
	font-size: 14px;
	margin-top: 30px;
	margin-left:22px;
	padding: 8px 20px;
	border-radius: 5px;
	border: 1px solid #D4D3E8;
	text-transform: uppercase;
	text-align: center;
	font-weight: 1000;
	align-items: center;
	width: 85%;
	color: #4C489D;
	box-shadow: 0px 2px 2px #5C5696;
	cursor: pointer;
	transition: .4s;
	float:left;
}

#joinSubmit:active,
#joinSubmit:focus,
#joinSubmit:hover {
	border-color: #6A679E;
	outline: none;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

function focusName(){
	$("#joinMsg").text("특수문자는 사용하실 수 없습니다.");
}

function focusBirth(){
	$("#joinMsg").text("2020-02-02 형식으로 입력해주세요.");
}

function checkName(){
	var name = $("#name").val();
	if(name == ""){
		$("#name").css("border-bottom-color", "red");
		$("#joinErr").text("닉네임을 작성해주세요.");
		$("#name").focus();
		$("#joinSubmit").prop("readonly", true);
		$("#name").prop("readonly", false);
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'name='+name,
		url: '../joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#name").css("border-bottom-color", "red");
				$("#joinErr").text("이미 등록된 닉네임 입니다.");
				$("#joinSubmit").prop("readonly", true);
				$("#name").prop("readonly", false);
			}else{
				$("#name").css("border-bottom-color", "#6A679E");
				$("#joinErr").text(" ");
				$("#joinSubmit").prop("readonly", false);
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
    
	if(birth != null){
    	$("#birth_date").css("border-bottom-color", "#6A679E");
    	$("#joinErr").text(" ");
		$("#joinSubmit").prop("readonly", false);
    	return true;
    } else if (birth.include('-') != 2 && birth.length() != 10){
    	$("#birth_date").css("border-bottom-color", "red");
		$("#joinErr").text("생년월일을 다시 확인해주세요.");
		$("#joinSubmit").prop("readonly", true);
		$("#birth_date").prop("readonly", false);
        return false;
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
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/view/component/header.jsp" />
		</div>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="joinBox">
			<h2 align="center">SNS 회원가입</h2>
			<div id="text_align">
				<span id="joinMsg">D'movie</span>
			</div>
			<div id="text_align">
				<span id="joinErr"> </span>
			</div>
			<form action="join.do" method="post">
				<br />
				<div>
					<input type="text" id="name" name="name" class="join_input" placeholder="닉네임" required="required" onchange="checkName()" oninput="handleOnInput(this)" onfocus="focusName()">
				</div>
					<input type="hidden" name="id" value="${snsUser.email}">
					<input type="hidden" type="password" name="password" value="${snsUser.access_token }">
					<input type="hidden" name="confirmPassword" type="hidden" value="${snsUser.access_token }">
					<input type="hidden" name="email" value="${snsUser.email}">
				<div>
					<input type="date" id="birth_date" name="birth_date" class="join_input" placeholder="생일" required="required" onchange="checkBirth()" onfocus="focusBirth()">
				</div>


				<input type="submit" id="joinSubmit" name="joinSubmit" class="join_input" value="가입하기">
			</form>
		</div>
	</div>
</body>
</html>