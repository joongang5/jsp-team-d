<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/joinForm.css" rel="stylesheet">
<link href="./css/main.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#sNOSubmit").prop("disabled", true);
});


function isSame() {
	var pw1 = $("#newPw").val();
	var pw2 = $("#confirmPassword").val();
	var same = document.getElementById('pwSame');
	var leng = document.getElementById('pwLength');
    
	if(pw1.length < 6){
    		leng.innerHTML='비밀번호를 6자 이상 입력해주세요.';
            leng.style.color='red';
            $("#sNOSubmit").prop("disabled", true);
            return false;
    }
	if(pw1.length > 5){
    		leng.innerHTML='비밀번호';
    		leng.style.color='blue';
    	if(pw1 == pw2 && pw2 == pw1) {
        	same.innerHTML='비밀번호';
            same.style.color='blue';
            $("#sNOSubmit").prop("disabled", false);
            return true;
        } else if(pw2 == "") {
        	same.innerHTML='비밀번호';
        	same.style.color='black';
        } else {
        	same.innerHTML='비밀번호가 일치하지 않습니다.';
            same.style.color='red';
            $("#sNOSubmit").prop("disabled", true);
        }
    } 
}

</script>


</head>
<body>

	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>
	<form action="setNewPw.do" method="post">
		<div>
			<span id="pwLength">비밀번호(6자리 이상)</span><span id="joinPwInstruction"><img id="join_help_icon" src="./img/join_help_icon.png"></span>
			<br><input type="password" id="newPw" name="newPw" required="required" onchange="isSame()">
		</div>
		<div>
			<span id="pwSame">비밀번호 확인</span>
			<br><input type="password" id="confirmPassword" name="confirmPassword" required="required" onchange="isSame()">
	
		</div>
		<input type="submit" id="sNOSubmit" value="암호 변경">
	</form>
</body>
</html>