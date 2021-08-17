<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/main.css" rel="stylesheet">
<style type="text/css">
#snoBox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
 	width:230px;
}

#text_align {
	text-align: center;
}

#snoMsg {
	font-size: 12px;
}

#snoErr {
	font-size: 10px;
}

.sno_input {
	border: none;
	color: white;
	border-bottom: 2px solid #D1D1D4;
	background: #212121;
	padding-left: 15px;
	padding-top: 12px;
	padding-bottom: 12px;
    margin-left: -10px;
	font-weight: 1000;
	width: 100%;
	transition: .4s;
}

.sno_input:active,
.sno_input:focus,
.sno_input:hover {
	background: #212121;
	outline: none;
	border-bottom-color: #6A679E;
}

#sNOSubmitBtn {
	background: #fff;
	font-size: 14px;
	margin: 25px 40px;
	padding: 5px 6px;
	border-radius: 5px;
	border: 1px solid #D4D3E8;
	text-transform: uppercase;
	text-align: center;
	font-weight: 1000;
	align-items: center;
	width: 65%;
	color: #4C489D;
	box-shadow: 0px 2px 2px #5C5696;
	cursor: pointer;
	transition: .4s;
	float:left;
}

#sNOSubmitBtn:active,
#sNOSubmitBtn:focus,
#sNOSubmitBtn:hover{
	border-color: #6A679E;
	outline: none;
}

</style>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#sNOSubmitBtn").prop("disabled", true);
});

function focusPw(){
	$("#snoErr").text("6자 이상 입력해주세요.");
}
function focusCPw(){
	$("#snoErr").text("비밀번호를 다시 한번 입력해주세요.");
}
function sNOSubmit(){
	$("#snoErr").text("다시 한번 눌러주세요.");
	$("#sNOSubmitBtn").prop("type", "submit");
}

function snoIsSame() {
	var pw1 = $("#newPw").val();
	var pw2 = $("#sNOconfirmPassword").val();
    
	if(pw1.length < 6 || pw1.length > 30){
			$("#snoErr").text("비밀번호를 6자 이상 입력해주세요.");
			$("#newPw").css("border-bottom-color", "red");
			$("#sNOSubmitBtn").prop("disabled", true);
            return false;
    }
	if(pw1.length > 5){
    	if(pw1 == pw2 && pw2 == pw1) {
    		$("#snoErr").text(" ");
			$(".sno_input").css("border-bottom-color", "#6A679E");
			$("#sNOSubmitBtn").prop("disabled", false);
            return true;
        } else if(pw2 == "") {
			$(".sno_input").css("border-bottom-color", "#6A679E");
        } else {
            $("#snoErr").text("비밀번호가 일치하지 않습니다.");
			$(".sno_input").css("border-bottom-color", "red");
			$("#sNOSubmitBtn").prop("disabled", true);
		}
    } 
}

</script>


</head>
<body>
	<div id="snoBox">
		<h2 align="center">비밀번호 재설정</h2>
		<div id="text_align"><span id="snoMsg">D'movie</span></div>
		<div id="text_align"><span id="snoErr"> </span></div>
		
		
		
		<c:if test="${! empty tempAuthUser}">
		<form action="${pageContext.request.contextPath }/setNewPw.do" method="post">
			<div>
				<input type="password" id="newPw" name="newPw" class="sno_input" placeholder="비밀번호" required="required" onchange="snoIsSame()" onfocus="focusPw()">
			</div>
			<div>
				<input type="password" id="sNOconfirmPassword" name="confirmPassword" class="sno_input" placeholder="비밀번호 확인" required="required" onchange="snoIsSame()" onfocus="focusCPw()">
			</div>
			<input id="sNOSubmitBtn" value="변경하기" onfocus="sNOSubmit()">
		</form>
		</c:if>
	</div>
</body>
</html>