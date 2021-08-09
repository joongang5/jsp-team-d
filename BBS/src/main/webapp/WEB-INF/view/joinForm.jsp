<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link href="./css/joinForm.css" rel="stylesheet">
<link href="./css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">


$(function(){
		$("#joinSubmit").prop("disabled", true);
});



function checkID(){
	var id = $("#id").val();
	var email = $("#email").val();
	if(id == "" || id.length < 4){
		$("#joinIdConfirm").css("color", "red");
		$("#joinIdConfirm").text("아이디를 네글자 이상 작성해주세요.");
		$("#id").focus();
		$("#joinSubmit").prop("disabled", true);
		return false;
	}
	if(id.includes('@') == 1 && id.includes('.') == true){
		$('#email').val($("#id").val());
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'id='+id,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#joinIdConfirm").css("color", "red");
				$("#joinIdConfirm").text("아이디 " + id + "는 이미 등록되어 있습니다.");
				$("#joinSubmit").prop("disabled", true);
				return false;
			}else{
				$("#joinIdConfirm").css("color", "blue");
				$("#joinIdConfirm").text("아이디");
				$("#joinSubmit").prop("disabled", false);
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
	if(name == "" || name.length < 2){
		$("#joinNameConfirm").css("color", "red");
		$("#joinNameConfirm").text("닉네임을 두글자 이상 작성해주세요.");
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



function isSame() {
	var pw1 = $("#password").val();
	var pw2 = $("#confirmPassword").val();
	var same = document.getElementById('pwSame');
	var leng = document.getElementById('pwLength');
    
	if(pw1.length < 6){
    		leng.innerHTML='비밀번호를 6자리 이상 입력해주세요.';
            leng.style.color='red';
            $("#joinSubmit").prop("disabled", true);
            return false;
    }
	if(pw1.length > 5){
    		leng.innerHTML='비밀번호';
    		leng.style.color='blue';
    	if(pw1 == pw2) {
        	same.innerHTML='비밀번호';
            same.style.color='blue';
            $("#joinSubmit").prop("disabled", false);
            return true;
        } else if(pw2 == pw1) {
       		same.innerHTML='비밀번호';
            same.style.color='blue';
            $("#joinSubmit").prop("disabled", false);
            return true;
        } else if(pw2 == "") {
        	same.innerHTML='비밀번호';
        	same.style.color='black';
        } else {
        	same.innerHTML='비밀번호가 일치하지 않습니다.';
            same.style.color='red';
            $("#joinSubmit").prop("disabled", true);
        }
    } 
}
	
	
	

function checkEmail(){
	var id = $("#id").val();
	var email = $("#email").val();
	if(email == "" || id.length < 5 || id.includes('.') != false || id.includes('@') != false){
		$("#joinEmailConfirm").text("이메일을 다시 확인해주세요.");
		$("#email").focus();
		$("#joinSubmit").prop("disabled", true);
	}
	if(id.includes('@') == 1 && id.includes('.') == true){
		if(id != email){
			$("#joinEmailConfirm").css("color", "red");
			$("#joinEmailConfirm").text("이메일로 아이디를 사용할 경우 아이디와 이메일이 같아야 합니다.");
			$("#id").focus();
			$("#joinSubmit").prop("disabled", true);
			return false;
		}
		if(email != id){
			$("#joinEmailConfirm").css("color", "red");
			$("#joinEmailConfirm").text("이메일로 아이디를 사용할 경우 아이디와 이메일이 같아야 합니다.");
			$("#id").focus();
			$("#joinSubmit").prop("disabled", true);
			return false;
		}
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'email='+email,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#joinEmailConfirm").css("color", "red");
				$("#joinEmailConfirm").text("이메일 " + email + "은(는) 이미 등록되어 있습니다.");
				$("#joinSubmit").prop("disabled", true);
			}else{
				$("#joinEmailConfirm").css("color", "blue");
				$("#joinEmailConfirm").text("이메일");
				$("#joinSubmit").prop("disabled", false);
				return true;
			}
		},
		error: function(xhr, status, e){
			alert("문제 발생 : " + e);
		}
	});
}

function checkEmail(){
	var id = $("#id").val();
	var email = $("#email").val();
	if(email == "" || id.length < 5 || id.includes('.') != true || id.includes('@') != true){
		$("#joinEmailConfirm").text("이메일을 다시 확인해주세요.");
		$("#email").focus();
	}
	if(id.includes('@') == 1 && id.includes('.') == true){
		if(id != email){
			$("#joinEmailConfirm").css("color", "red");
			$("#joinEmailConfirm").text("이메일로 아이디를 사용할 경우 아이디와 이메일이 같아야 합니다.");
			$("#id").focus();
			$("#joinSubmit").prop("disabled", true);
			return false;
		}
		if(email != id){
			$("#joinEmailConfirm").css("color", "red");
			$("#joinEmailConfirm").text("이메일로 아이디를 사용할 경우 아이디와 이메일이 같아야 합니다.");
			$("#id").focus();
			$("#joinSubmit").prop("disabled", true);
			return false;
		}
	}
	$.ajax({
		type:'post',
		dataType:'text',
		data: 'email='+email,
		url: './joinCheck',
		success: function(rData, textStatus, xhr){
			if(rData == 1){
				$("#joinEmailConfirm").css("color", "red");
				$("#joinEmailConfirm").text("이메일 " + email + "은(는) 이미 등록되어 있습니다.");
				$("#joinSubmit").prop("disabled", true);
			}else{
				$("#joinEmailConfirm").css("color", "blue");
				$("#joinEmailConfirm").text("이메일");
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
    	$("#joinSubmit").css("color", "black");
    	bc.innerHTML='생일';
        bc.style.color='blue';
        $("#joinSubmit").prop("disabled", false);
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
		<div>
			<span id="joinIdConfirm">아이디</span><span id="joinIdInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="text" id="id" name="id" required="required" onchange="checkID()" oninput="handleOnEmail(this)">
		</div>
		<div>
			<span id="joinNameConfirm">닉네임</span><span id="joinNameInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="text" id="name" name="name" required="required" onchange="checkName()" oninput="handleOnInput(this)">
		</div>
		<div>
			<span id="pwLength">비밀번호(6자리 이상)</span><span id="joinPwInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="password" id="password" name="password" required="required" onchange="isSame()">
		</div>
		<div>
			<span id="pwSame">비밀번호 확인</span>
			<br><input type="password" id="confirmPassword" name="confirmPassword" required="required" onchange="isSame()">
	
		</div>
		<div>
			<span id="joinEmailConfirm">이메일</span><span id="joinEmailInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="email" id="email" name="email" required="required" style="text-transform: lowercase" onchange="checkEmail()" onchange="checkConfirmEmail()" oninput="handleOnEmail(this)">
		</div>
		<div>
			<span id="birthConfirm">생일</span><span id="joinBirthInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="date" id="birth_date" name="birth_date" required="required" onchange="checkBirth()">
		</div>
		<div></div>
		
		
		<input type="image" id="joinSubmit" name="joinSubmit" src="./img/join_confirm_icon.png" alt="submit">
	</form>
</body>
</html>