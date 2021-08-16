<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.home {
	position: relative;
	width: 146px;
	height: 31px;
	border: 2px solid #2b2b2b;
	font-weight: bold;
	color: white;
	background-color: black;
	cursor: pointer;
}
label {
	text-align: center;
	font-weight: 0;
	transition: .4s;

}

.headerButton:active,
.headerButton:focus,
.headerButton:hover {
	color: black;
	transition: .4s;
}

#logo {
	position: absolute;
	top: 55%;
	left: 50%;
	transform: translate(-50%, -50%);
	cursor: pointer;
}

#modalLogin {
	background-color: #212121;
}

#closeModal {
	position: relative;
	top: -20px;
	float: right;
	z-index: 5;
}

#closeModal:active,
#closeModal:focus,
#closeModal:hover {
	color: gray;
	transition: .4s;
}

#headerButtons {
	position: absolute;
	top: 0;
	width: calc(100% - 160px);
	right: 10px;
	height: 100%;
	color: white;
}

input[id*="popup"] {
	display: none;
}

input[id*="popup"]+label {
	display: inline-block;
	padding: 10px;
	color: #fff;
	float: right;
}

input[id*="popup"]+label+label {
	display: inline-block;
	padding: 10px;
	color: #fff;
	float: right;
}

input[id*="popup"]+label[id*="login"] {
	display: hidden;
}



input[id*="popup"]+label+div {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 100;
	opacity: 0;
	visibility: hidden;
	transition: all 0.5s;
}

input[id*="popup"]:checked+label+div {
	opacity: 1;
	visibility: visible;
	background: rgba(0, 0, 0, .7);
}

input[id*="popup"]+label+div>div {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 300px;
	height: 300px;
	background: #212121;
	z-index: 2;
}

input[id*="popup"]+label+div>div>label {
	position: absolute;
	top: -20px;
	float: right;
}

input[id*="popup"]+label+div>label {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, .8);
	z-index: 1;
}




input[id*="joinPopup"] {
	display: none;
}

input[id*="joinPopup"]+label {
	display: inline-block;
	padding: 10px;
	color: #fff;
}

input[id*="joinPopup"]+label[id*="join"] {
	display: hidden;
}

input[id*="joinPopup"]+label+div {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 100;
	opacity: 0;
	visibility: hidden;
	transition: all 0.5s;
}

input[id*="joinPopup"]:checked+label+div {
	opacity: 1;
	visibility: visible;
}

input[id*="joinPopup"]+label+div>div {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 300px;
	height: 500px;
	background: #212121;
	z-index: 2;
}

input[id*="joinPopup"]+label+div>div>label {
	position: absolute;
	top: -20px;
	float: right;
}

input[id*="joinPopup"]+label+div>label {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 1;
}

</style>

<script>
$(document).ready(function() {
	var curPath = $(location).attr('pathname');
	var split = curPath.split('/');
	var naviId = '';
	if (split.length > 2)
		naviId = split[2];
	
	if (naviId == '' || naviId == 'boxOffice') {
		$('#headerButtons').css('right', '');
		$('#headerButtons').css('width', '500px');
		$('#headerButtons').css('left', '460px');	
	}
});

function onHomeClick() {
	location.href = '/BBS/boxOffice/list.do';
}

function onMyPageClick() {
	location.href = '/BBS/myPage.do';
}

function onLogoutClick() {
	location.href = '/BBS/logout.do';
}
</script>

<div class="home">
	<div id="logo" onclick="onHomeClick();">D'movie</div>
</div>

<div id="headerButtons">
	<input type="checkbox" id="popup">
	
	<c:if test="${empty authUser}">
		<label id="login" class="headerButton" for="popup">로그인</label>
	</c:if>

	<c:if test="${! empty authUser}">
		<label class="headerButton" onclick="onLogoutClick();">로그아웃</label>
		<label class="headerButton" onclick="onMyPageClick();">${authUser.name}님 마이페이지</label>
	</c:if>
	
	<c:if test="${empty authUser}">
	<div>
		<div id="modalLogin">
			<label id="closeModal" for="popup">X</label>
			<c:import url="/WEB-INF/view/loginForm.jsp" />
		</div>
		<label for="popup"></label>
	</div>
	
	
		<input type="checkbox" id="joinPopup">
			<label id="join" for="joinPopup"></label>
		<div>
			<div id="modalLogin">
				<label id="closeModal" for="joinPopup">X</label>
				<c:import url="/WEB-INF/view/joinForm.jsp" />
			</div>
				<label for="joinPopup"></label>
		</div>
			
	
	
	
	</c:if>
</div>


