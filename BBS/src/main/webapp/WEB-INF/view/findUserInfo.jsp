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
#fpwBox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
 	width:230px;
}

#text_align {
	text-align: center;
}

#fpwMsg {
	font-size: 12px;
}

#fpwErr {
	font-size: 10px;
}

.fpw_input {
	border: none;
	color: white;
	border-bottom: 2px solid #D1D1D4;
	background: #212121;
	padding-left: 15px;
	padding-bottom: 5px;
    margin-left: -10px;
	font-weight: 1000;
	width: 100%;
	transition: .4s;
}

.fpw_input:active,
.fpw_input:focus,
.fpw_input:hover {
	background: #212121;
	outline: none;
	border-bottom-color: #6A679E;
}

#authBtn, #emailBtn {
	background: #fff;
	font-size: 14px;
	margin: 15px 20px;
	padding: 4px 5px;
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

#authBtn:active, #emailBtn:active,
#authBtn:focus, #emailBtn:focus,
#authBtn:hover, #emailBtn:hover {
	border-color: #6A679E;
	outline: none;
}

</style>

<script type="text/javascript">

function focusFPW(){
	$("#fpwErr").text("가입하신 아이디나 이메일을 입력해주세요.");
	$("#pwChangKey").prop("readonly", true);
	$("#authBtn").prop("readonly", true);
}

function focusFpwChgkey(){
	$("#fpwErr").text("인증번호를 입력하세요.");
	$("#authBtn").prop("readonly", false);
}

function clickEmailBtn(){
	$("#fpwErr").text("잠시만 기다려주세요.");
	$("#pwChangKey").prop("readonly", false);
	$("#authBtn").prop("readonly", false);
}
</script>

</head>
<body>

	<div id="fpwBox">
		<h2 align="center">비밀번호 찾기</h2>
		<div id="text_align"><span id="fpwMsg">D'movie</span></div>
		<div id="text_align"><span id="fpwErr"> 
			<c:if test="${param.fpwvalue eq 'sns'}">SNS 가입자는 비밀번호를 변경 할 수 없습니다.</c:if>
			<c:if test="${param.fpwvalue eq 'none'}">가입된 정보가 없습니다.</c:if>
			<c:if test="${param.fpwvalue eq 'success'}">이메일로 인증번호가 발송되었습니다.</c:if>
			<c:if test="${param.fpwvalue eq 'done'}"><br><br>변경이 완료되었습니다.<br>다시 로그인 해주세요.</c:if>
				
		</span></div>
		
		<c:if test="${param.fpwvalue ne 'done'}">
			<form action ="${pageContext.request.contextPath }/forgot.do" method="post">
				 <input type="text" id="id" name="id" class="fpw_input" placeholder="아이디 혹은 이메일" onfocus="focusFPW()">
				 <input type="submit" id="emailBtn" name="emailBtn" value="인증 메일 보내기" onclick="clickEmailBtn()">	
			</form>	
			<form action ="${pageContext.request.contextPath }/setNewPw.do" method="post">
				 <input type="text" id="pwChangKey" class="fpw_input" placeholder="인증번호를 입력하세요." required="required" name="pwChangeKey" onfocus="focusFpwChgkey()">
				 <input type="submit" id="authBtn" name="authBtn" value="확인">
			</form>
		</c:if>
</div>
</body>
</html>