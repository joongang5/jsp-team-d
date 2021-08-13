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

.home > div {
	position: absolute;
	top: 50%;
	left: 45%;
	transform: translate(-50%, -50%);
	cursor: pointer;
}

#headerButtons {
	position: absolute;
	top: 0;
	left: 750px;
	width: 210px;
	height: 100%;
	background-color: red;
}

#headerButtons > button {
	position: relative;
	top: 50%;
	transform: translate(0, -50%);
	margin: 0;
	width: 100px;
}

#headerButtons > button + button {
	position: relative;
	top: 50%;
	transform: translate(0, -50%);
	margin: 0;
	width: 100px;
}


input[id*="popup"] {
	display:none;
}
input[id*="popup"] + label {
	display:inline-block;
	padding:20px;
	background:#ffcd41;
	color:#fff;
}
input[id*="popup"] + label + div {
	position:fixed;
	top:0;
	left:0;
	width:100%;
	height:100%;
	z-index:100;
}
input[id*="popup"] + label + div {
	display:none;
}

input[id*="popup"]:checked + label + div {
	display:block;
}

input[id*="popup"] + label + div > div {
	position:absolute;
	top:50%;
	left:50%;
	transform:translate(-50%,-50%);
	width:500px;
	height:300px;
	background:#fff;
	z-index:2;
}
input[id*="popup"] + label + div > div > label {
	position:absolute;
	top:0%;
	left:0%;
	transform:translate(40%,-40%);
	padding:20px;
	background:#dd5347;
	border-radius:100%;
	z-index:1;
}
input[id*="popup"] + label + div > label {
	position:absolute;
	top:0;
	left:0
	width:100%;
	height:100%;
	background:rgba(0,0,0,.9);
	z-index:1;
}




</style>

<script>
	function onHomeClick() {
		location.href = '/BBS/boxOffice/list.do';
	}
	
	function onLoginClick() {
		$("#modalLogin").load("login.do");
	}

	function onMyPageClick() {
		location.href = '/BBS/myPage.do';
	}
</script>

<div class="home">
	<div onclick="onHomeClick();">D'Movie</div>
</div>
<div id="headerButtons">

	<input type="checkbox" id="popup">
	<label for="popup" onclick="onLoginClick();"></label>
	<div>
	<div>
		<label for="popup"></label>
	</div>
	<label for="popup" id="modalLogin"></label>
		
	</div>
	
	<button onclick="onMyPageClick();">마이페이지</button>
</div>

