<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
<style type="text/css">
#mypwBox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
 	width:230px;
}

#text_align {
	text-align: center;
}

#mypwMsg {
	font-size: 12px;
}

#mypwErr {
	font-size: 10px;
}

.mypw_input {
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

.mypw_input:active,
.mypw_input:focus,
.mypw_input:hover {
	background: #212121;
	outline: none;
	border-bottom-color: #6A679E;
}

#mypwSubmit {
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

#mypwSubmit:active,
#mypwSubmit:focus,
#mypwSubmit:hover{
	border-color: #6A679E;
	outline: none;
}

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	$("#mypwSubmit").prop("readonly", true);
});

function focusPw(){
	$("#mypwErr").text("기존에 사용한 비밀번호를 입력해주세요.");
}
function focusNPw(){
	$("#mypwErr").text("6자 이상 입력해주세요.");
}
function focusCPw(){
	$("#mypwErr").text("비밀번호를 다시 한번 입력해주세요.");
	
}
function mypwSubmit(){
	$("#mypwErr").text("다시 한번 눌러주세요.");
	$("#mypwSubmit").prop("type", "submit");
}

function mypwIsSame() {
	var pw1 = $("#newPw").val();
	var pw2 = $("#mypwconfirmPassword").val();
    
	if(pw1.length < 6 || pw1.length > 30){
			$("#mypwErr").text("비밀번호를 6자 이상 입력해주세요.");
			$("#newPw").css("border-bottom-color", "red");
            return false;
    }
	if(pw1.length > 5){
    	if(pw1 == pw2 && pw2 == pw1) {
    		$("#mypwErr").text(" ");
			$(".mypw_input").css("border-bottom-color", "#6A679E");
			$("#mypwSubmit").prop("readonly", false);
            return true;
        } else if(pw2 == "") {
			$(".mypw_input").css("border-bottom-color", "#6A679E");
        } else {
            $("#mypwErr").text("비밀번호가 일치하지 않습니다.");
			$(".mypw_input").css("border-bottom-color", "red");
			$("#mypwSubmit").prop("readonly", true);
		}
    } 
}

</script>


</head>
<body>
	<div id="mypwBox">
		<h2 align="center">비밀번호 변경</h2>
		<div id="text_align"><span id="mypwMsg">D'movie</span></div>
		<div id="text_align"><span id="mypwErr"> </span></div>

		<form action="${pageContext.request.contextPath }/changePw.do" method="post">
			
			
			<div>
				<input type="password" id="curPw" name="curPw" class="mypw_input" placeholder="현재 비밀번호" required="required" onchange="snoIsSame()" onfocus="focusPw()">
			</div>
			<div>
				<input type="password" id="newPw" name="newPw" class="mypw_input" placeholder="새로운 비밀번호" required="required" onchange="snoIsSame()" onfocus="focusNPw()">
			</div>
			<div>
				<input type="password" id="mypwconfirmPassword" name="mypwconfirmPassword" class="mypw_input" placeholder="새로운 비밀번호 확인" required="required" onchange="mypwIsSame()" onfocus="focusCPw()">
			</div>
			<input type="submit" id="mypwSubmit" value="변경하기" onclick="mypwSubmit()">
		</form>
		
	</div>
	

</body>
</html>