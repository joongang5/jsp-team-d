<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link href="./css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">




function isSame() {
	var pw1 = $("#password").val();
	var pw2 = $("#confirmPassword").val();
	var same = document.getElementById('pwSame');
	var leng = document.getElementById('pwLength');
    
	if(pw1.length < 6){
    		leng.innerHTML='비밀번호를 6자리 이상 입력해주세요.';
            leng.style.color='red';
            return false;
    } else if(pw1.length > 5){
    		leng.innerHTML='비밀번호';
    		leng.style.color='blue';
    	if(pw1 == pw2 && pw2.length > 5) {
        	same.innerHTML='비밀번호';
            same.style.color='blue';
        }
        else if(pw2 == pw1) {
       		same.innerHTML='비밀번호';
            same.style.color='blue';
        } else if(pw2 == "") {
        	same.innerHTML='비밀번호';
        	same.style.color='black';
        }
        else {
        	same.innerHTML='비밀번호가 일치하지 않습니다.';
            same.style.color='red';
            return false;
        }
    } 
}
	
	
	
function checkID(){
	//var id = $(선택자).명령();
	var id = $("#id").val();
	if(id == "" || id.length < 4){
		$("#joinIdConfirm").css("color", "red");
		$("#joinIdConfirm").text("아이디를 네글자 이상 작성해주세요.");
		$("#id").focus();
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'id='+id,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				//$("#joinSubmit").prop("disabled", true);//비활성화
				$("#joinIdConfirm").css("color", "red");
				$("#joinIdConfirm").text("아이디 " + id + "는 이미 등록되어 있습니다.");
				
			}else{
				//$("#joinSubmit").prop("disabled", false);//활성화
				$("#joinIdConfirm").css("color", "blue");
				$("#joinIdConfirm").text("아이디");
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}

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

function checkEmail(){
	//var id = $(선택자).명령();
	var email = $("#email").val();
	if(email == "" || id.length < 5){
		$("#joinEmailConfirm").text("이메일을 다시 확인해주세요.");
		$("#email").focus();
		return false;
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'email='+email,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				//$("#joinSubmit").prop("disabled", true);//비활성화
				$("#joinEmailConfirm").css("color", "red");
				$("#joinEmailConfirm").text("이메일 " + email + "은(는) 이미 등록되어 있습니다.");
				
			}else{
				//$("#joinSubmit").prop("disabled", false);//활성화
				$("#joinEmailConfirm").css("color", "blue");
				$("#joinEmailConfirm").text("이메일");
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
		<div>
			<span id="joinIdConfirm">아이디</span>
			<br><input type="text" id="id" name="id" required="required" onchange="checkID()" oninput="handleOnEmail(this)">
		</div>
		<div>
			<span id="joinNameConfirm">닉네임</span>
			<br><input type="text" id="name" name="name" required="required" onchange="checkName()" oninput="handleOnInput(this)">
		</div>
		<div>
			<span id="pwLength">비밀번호</span>
			<br><input type="password" id="password" name="password" required="required" onchange="isSame()">
		</div>
		<div>
			<span id="pwSame">비밀번호 확인</span>
			<br><input type="password" id="confirmPassword" name="confirmPassword" required="required" onchange="isSame()">
	
		</div>
		<div>
			<span id="joinEmailConfirm">이메일</span>
			<br><input type="email" id="email" name="email" required="required" style="text-transform: lowercase" onchange="checkEmail()" oninput="handleOnEmail(this)">
		</div>
		<div>
			생일<br><input type="date" name="birth_date" required="required">
		</div>
		<div></div>

		<input type="submit" id="joinSubmit" value="가입하기">
	</form>
</body>
</html>