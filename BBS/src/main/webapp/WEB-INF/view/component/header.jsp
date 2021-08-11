<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	top: 50%;
	left: 800px;
	transform: translate(-50%, -50%);
}

#headerButtons > button {
	margin: 0;
	float: left;
}

#headerButtons > button + button {
	margin: 0;
	float: left;
}
</style>

<script>
	function onHomeClick() {
		location.href = '/BBS/boxOffice/list.do';
	}
	
	function onLoginClick() {
		$("#mainWrapper").load("login.do");
	}

	function onMyPageClick() {
		location.href = '/BBS/myPage.do';
	}
</script>

<div class="home">
	<div onclick="onHomeClick();">D'Movie</div>
</div>
<div id="headerButtons">
	<button onclick="onLoginClick();">로그인</button>
	<button onclick="onMyPageClick();">마이페이지</button>
</div>

