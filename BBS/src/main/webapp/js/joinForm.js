$(function(){
		$("#joinSubmit").prop("disabled", true);
});


//$("#id, #name, #password, #confirmPassword, #email, #birth").prop("readonly", true);

function checkID(){
	var id = $("#id").val();
	var email = $("#email").val();
	if(id == "" || id.length < 4){
		$("#joinIdConfirm").css("color", "red");
		$("#joinIdConfirm").text("아이디를 4자 이상 작성해주세요.");
		$("#id").focus();
		$("#joinSubmit").prop("disabled", true);
		return false;
	}
	if(id.includes('@') == 1 && id.includes('.') == true){
		$("#email").val($("#id").val());
		$("#email").prop("readonly", true);
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



function isSame() {
	var pw1 = $("#password").val();
	var pw2 = $("#confirmPassword").val();
	var same = document.getElementById('pwSame');
	var leng = document.getElementById('pwLength');
    
	if(pw1.length < 6 || pw1.length > 30){
    		leng.innerHTML='비밀번호를 6자 이상 입력해주세요.';
            leng.style.color='red';
            $("#joinSubmit").prop("disabled", true);
            return false;
    }
	if(pw1.length > 5){
    		leng.innerHTML='비밀번호';
    		leng.style.color='blue';
    	if(pw1 == pw2 && pw2 == pw1) {
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
	  e.value = e.value.replace(/[^a-z0-9@.-_]/ig, '')
	}
