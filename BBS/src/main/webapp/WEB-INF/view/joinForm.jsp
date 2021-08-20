<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<style type="text/css">

#joinbox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
}

#snsUserBox {
	visibility:hidden;
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

#joinConfirm {
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

#joinConfirm:active,
#joinConfirm:focus,
#joinConfirm:hover {
	border-color: #6A679E;
	outline: none;
}

</style>
<script type="text/javascript">


function focusID(){
	$("#joinMsg").text("4자 이상, 이메일도 사용 가능합니다.");
}
function focusName(){
	$("#joinMsg").text("특수문자는 사용하실 수 없습니다.");
}
function focusPw(){
	$("#joinMsg").text("6자 이상 입력해주세요.");
}
function focusEmail(){
	$("#joinMsg").text("아이디를 이메일로 사용시 향후 변경이 불가합니다.");
}
function focusBirth(){
	$("#joinMsg").text("2020-02-02 형식으로 입력해주세요.");
}

function joinConfirm(){
	var iD = $("#joinIdDummy").val();
	var nD = $("#joinNameDummy").val();
	var pD = $("#joinPwDummy").val();
	var eD = $("#joinEmailDummy").val();
	var bD = $("#joinBirthDummy").val();

	if (iD == "true" && nD == "true" && pD == "true" && eD == "true" && bD == "true"){
		$("#joinSubmit").prop("type", "submit");
		$("#joinConfirm").prop("type", "hidden");
		$(".join_input").prop("readonly", "true");
		$("#joinErr").text("변경하기를 눌러주세요.");
	} else {
		$("#joinSubmit").prop("type", "hidden");
		$("#joinConfirm").prop("type", "submit");
		$("#joinErr").text("입력하신 정보를 다시 확인해주세요.");
	}
}

function checkID(){
	var id = $("#id").val();
	var email = $("#email").val();
	var agent = navigator.userAgent.toLowerCase();
	
	// 아이디 길이 체크
	if(id == "" || id.length < 4){
		$("#id").css("border-bottom-color", "red");
		$("#joinErr").text("아이디를 4자 이상 작성해주세요.");
		$("#id").focus();
		$("#joinIdDummy").val("false");
	}
	
	// 아이디가 이메일 형식인 경우
	if(id.indexOf('@') >= 0 && id.indexOf('@') >= 0){
		$("#email").val($("#id").val());
		$("#id").css("border-bottom-color", "#6A679E");
		$("#email").css("border-bottom-color", "#6A679E");
		$("#joinErr").text(" ");
		$("#email").prop("readonly", true);
	}
	
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'id='+id,
		url: '/BBS/joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#id").css("border-bottom-color", "red");
				$("#joinErr").text("이미 등록된 아이디 입니다.");
				$("#joinIdDummy").val("false");
			}else{
				$("#id").css("border-bottom-color", "#6A679E");
				$("#joinErr").text(" ");
				$("#joinIdDummy").val("true");
				return true;
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}



function checkName(){
	var name = $("#name").val();
	var agent = navigator.userAgent.toLowerCase();
	
	if(name == ""){
		$("#name").css("border-bottom-color", "red");
		$("#joinErr").text("닉네임을 작성해주세요.");
		$("#name").focus();
		$("#joinNameDummy").val("false");
	}
	
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'name='+name,
		url: '/BBS/joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#name").css("border-bottom-color", "red");
				$("#joinErr").text("이미 등록된 닉네임 입니다.");
				$("#joinNameDummy").val("false");
			}else{
				$("#name").css("border-bottom-color", "#6A679E");
				$("#joinErr").text(" ");
				$("#joinNameDummy").val("true");
				return true;
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}
function isSame() {
	var pw1 = $("#password").val();
	var pw2 = $("#confirmPassword").val();
    
	if(pw1.length < 6 || pw1.length > 30){
			$("#joinErr").text("비밀번호를 6자 이상 입력해주세요.");
			$("#password").css("border-bottom-color", "red");
			$("#joinPwDummy").val("false");
            return false;
    }
	if(pw1.length > 5){
    	if(pw1 == pw2 && pw2 == pw1) {
    		$("#joinErr").text(" ");
			$("#password").css("border-bottom-color", "#6A679E");
			$("#confirmPassword").css("border-bottom-color", "#6A679E");
			$("#joinPwDummy").val("true");
            return true;
        } else if(pw2 == "") {
        	$("#joinErr").text("비밀번호를 한번 더 입력해주세요.");
			$("#confirmPassword").css("border-bottom-color", "red");
        } else {
            $("#joinErr").text("비밀번호가 일치하지 않습니다.");
			$("#password").css("border-bottom-color", "red");
			$("#confirmPassword").css("border-bottom-color", "red");
			$("#joinPwDummy").val("false");
        }
    } 
}
	
	
	
function checkEmail(){
	var id = $("#id").val();
	var email = $("#email").val();
	var agent = navigator.userAgent.toLowerCase();
	
	if(email == "" || id.length < 5 || id.indexOf('.') == -1 || id.indexOf('@') == -1){
		$("#joinErr").text("이메일을 다시 확인해주세요.");
		$("#email").css("border-bottom-color", "red");
		$("#email").focus();
		$("#joinEmailDummy").val("false");
	}
	if(id.indexOf('@') >= 0 && id.indexOf('.') >= 0){
		if(id != email){
			$("#email").css("border-bottom-color", "red");
			$("#joinErr").text("아이디와 이메일을 똑같이 입력해주세요.");
			$("#id").focus();
			$("#email").prop("readonly", false);
			$("#joinEmailDummy").val("false");
		}
		if(email != id){
			$("#email").css("border-bottom-color", "red");
			$("#joinErr").text("아이디와 이메일을 똑같이 입력해주세요.");
			$("#id").focus();
			$("#email").prop("readonly", false);
			$("#joinEmailDummy").val("false");
		}
	}
	
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'email='+email,
		url: '/BBS/joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#email").css("border-bottom-color", "red");
				$("#joinErr").text("이미 등록된 이메일입니다.");
				$("#joinEmailDummy").val("false");
			}else{
				$("#email").css("border-bottom-color", "#6A679E");
				$("#joinErr").text(" ");
				$("#joinEmailDummy").val("true");
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
    	$("#joinBirthDummy").val("true");
    	return true;
    } else if (birth.include('-') != 2 && birth.length() != 10){
    	$("#birth_date").css("border-bottom-color", "red");
		$("#joinErr").text("생년월일을 다시 확인해주세요.");
		$("#joinBirthDummy").val("false");
    }
}

function handleOnInput(e)  {
	  e.value = e.value.replace(/[^ㄱ-힣a-zA-Z0-9]/ig, '')
	}

function handleOnEmail(e)  {
	  e.value = e.value.replace(/[^a-z0-9@.-_]/ig, '')
	}

	
</script>

</head>
<body>
	<div id="joinBox" class="modalBox">
		<h2 align="center">회원가입</h2>
	<div id="text_align"><span id="joinMsg">D'movie</span></div>
	<div id="text_align"><span id="joinErr"> </span></div>
		
			<c:if test="${empty snsUser}">
		<form action="${pageContext.request.contextPath }/join.do" method="post" autocomplete="off">
				<div>
					<input type="text" id="id" name="id" class="join_input" placeholder="아이디" required="required" onchange="checkID()" oninput="handleOnEmail(this)" onfocus="focusID()">
				</div>
				<div>
					<input type="text" id="name" name="name" class="join_input" placeholder="닉네임" required="required" onchange="checkName()" oninput="handleOnInput(this)" onfocus="focusName()">
				</div>
				<div>
					<input type="password" id="password" name="password" class="join_input" placeholder="비밀번호" required="required" onchange="isSame()" onfocus="focusPw()">
				</div>
				<div>
					<input type="password" id="confirmPassword" name="confirmPassword" class="join_input" placeholder="비밀번호 확인" required="required" onchange="isSame()">
				</div>
				<div>
					<input type="email" id="email" name="email" class="join_input" placeholder="이메일" required="required" style="text-transform: lowercase" onchange="checkEmail()" onchange="checkConfirmEmail()" oninput="handleOnEmail(this)" onfocus="focusEmail()">
				</div>
			
				<div>
					<input type="date" id="birth_date" name="birth_date" class="join_input" placeholder="생일" required="required" onchange="checkBirth()" onfocus="focusBirth()">
				</div>
			
					<input type="hidden" id="joinSubmit" name="joinSubmit" value="가입하기">
		</form>
					<input type="submit" id="joinConfirm" name="joinConfirm" value="확인하기" onclick="joinConfirm()">
		
			</c:if>
	</div>
</body>
					<input type="hidden" id="joinIdDummy" value="">
					<input type="hidden" id="joinNameDummy" value="">
					<input type="hidden" id="joinPwDummy" value="">
					<input type="hidden" id="joinEmailDummy" value="">
					<input type="hidden" id="joinBirthDummy" value="">
</html>