<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

<meta charset="UTF-8">
<title>Login</title>
<link href="./css/main.css" rel="stylesheet">
<style type="text/css">
#loginbox {
	position: absolute;
 	left: 50%;
 	transform: translateX(-50%);
}

#text_align {
	text-align: center;
}

#joinWelcome{
	font-size: 11px;
}

.login_input {
	border: none;
	color: white;
	border-bottom: 2px solid #D1D1D4;
	background: none;
	padding: 15px;
	padding-left: 24px;
	font-weight: 700;
	width: 75%;
	transition: .4s;
}

.login_input:active,
.login_input:focus,
.login_input:hover {
	outline: none;
	border-bottom-color: #6A679E;
}

.login_submit {
	background: #fff;
	font-size: 14px;
	margin-top: 20px;
	margin-left:30px;
	padding: 8px 20px;
	border-radius: 5px;
	border: 1px solid #D4D3E8;
	text-transform: uppercase;
	text-align: center;
	font-weight: 1000;
	align-items: center;
	width: 61%;
	color: #4C489D;
	box-shadow: 0px 2px 2px #5C5696;
	cursor: pointer;
	transition: .4s;
	float:left;
}

.login_submit:active,
.login_submit:focus,
.login_submit:hover {
	border-color: #6A679E;
	outline: none;
}

#kakao_button {
	margin-top: 20px;
    margin-right: 30px;
	border: 1px solid #D4D3E8;
	border-radius: 5px;
	float: right;
	
}

#kakao_button:active,
#kakao_button:focus,
#kakao_button:hover {
	border-color: #6A679E;
	transition: .4s;
}

#button_join {
	padding: 5px 20px;
	float: left;
	font-weight: 100;
	font-size: small;
	color: white;
	text-decoration: none;
}

#button_forgot {
	padding: 5px 20px;
	float: right;
	font-weight: 100;
	font-size: small;
	color: white;
	text-decoration: none;
}

#button_join:active, #button_forgot:active,
#button_join:focus, #button_forgot:focus,
#button_join:hover, #button_forgot:hover{
	color: gray;
	transition: .4s;
}

a:visited {
	text-decoration: none;
}


</style>
</head>
<body>




	<div id="container">

		<div id="loginBox" class="modalBox">
			<!-- ?????????, ???????????? ?????? ?????? / ????????? -->
			<!-- 
		<c:if test="${! empty authUser}">
			
			<h1>${authUser.name}??? ????????? ???????????????.</h1>
				<a href="/BBS/logout.do"><button>????????????</button></a>
			<c:if test="${empty snsAuthUser}">
				<a href="/BBS/changePw.do"><button>????????????</button></a>
			</c:if>
		</c:if>
		
		-->

			<c:if test="${empty authUser}">
				<c:if test="${empty param.joinvalue}">
					<br>
						<h2 align="center">?????????</h2>
				</c:if>
				<c:if test="${param.joinvalue eq 'done'}">
					<h2 align="center">?????????</h2>
					<div id="text_align">
						<span id="joinWelcome">
							????????? ?????????????????????.<br>?????? ????????? ????????????.
						</span>
					</div>
				</c:if><c:if test="${param.joinvalue eq 'um'}">
					<h3 align="center">?????????</h3>
					<div id="text_align">
						<span id="joinWelcome">
							????????? ????????? ???????????? ????????????.<br>?????? ?????? ??? ????????? ????????????.
						</span>
					</div>
				</c:if>

			<form action="${pageContext.request.contextPath }/login.do" method="post">
					
					<div class="login_field" align="center">
						<input type="text" id="loginId" class="login_input" placeholder="?????????" name="id" required="required" value="${param.id }">
					</div>
					
					<div class="login_field" align="center">
						<input type="password" id="loginPassword" class="login_input" name="password" placeholder="????????????" required="required" value="${param.pw }">
					</div>
						<a id="button_join"><label id="join" for="joinPopup">????????????</label></a>
						<a id="button_forgot"><label id="join" for="fogotPopup">???????????? ??????</label></a>
					
					<c:if test="${errors.idOrPwNotMatch }"> ???????????? ??????????????? ???????????? ????????????.</c:if>
					<input type="submit" class="login_submit" value="?????????">
			</form>
				
				

				<c:set var="clientId" value="188766d70b45863a165fa74d7d8a455b" />
				<c:set var="redirectUri" value="http://localhost:8080/BBS/login.do" />
				<br>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code">
					<img id="kakao_button" src="${pageContext.request.contextPath }/img/kakao_login_small.png">
				</a>


				<!-- 
<a href="https://kauth.kakao.com/oauth/logout?client_id=${clientId}&logout_redirect_uri=${logout_redirectUri}"><button type="submit">????????? ????????????</button></a>
-->

			</c:if>


		</div>
	</div>
</body>
</html>