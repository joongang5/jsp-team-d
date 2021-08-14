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
	height: 100px;
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
	padding:10px;
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
	opacity:0;
	visibility: hidden;
	transition:all 0.5s;
}

input[id*="popup"]:checked + label + div {
	opacity:1;
	visibility: visible;
	background:rgba(0,0,0,.6);
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
	right:0%;
	transform:translate(40%,-40%);
	padding:15px;
	background:#dd5347;
	border-radius:100%;
	z-index:1;
}
input[id*="popup"] + label + div > label {
	position:absolute;
	top:0;
	left:0;
	width:100%;
	height:100%;
	background:rgba(0,0,0,.8);
	z-index:1;
}



</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<meta charset="UTF-8">
<script>


	function onHomeClick() {
		location.href = '/BBS/boxOffice/list.do';
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
	<label for="popup">로그인</label>
	<div>
		<div id="modalLogin">
		<label for="popup" >X</label>
		
		<div id="container">
	
		<div id="loginBox">
		<!-- 로그인, 로그아웃 관련 기능 / 이강민 -->
		<c:if test="${! empty authUser}">
			
			<h1>${authUser.name}님 로그인 되었습니다.</h1>
				<a href="../logout.do"><button>로그아웃</button></a>
			<c:if test="${empty snsAuthUser}">
			<!-- sns 로그인 사용자들만 암호변경 사용불가 -->
				<a href="../changePw.do"><button>암호변경</button></a>
			</c:if>
		</c:if>
			
		<c:if test="${empty authUser}">
			
			<form action="../login.do" method="post">
				<span id="inputId"> ID : <input type="text" placeholder="아이디를 입력해주세요" name="id" required="required" value="${param.id }">
					<c:if test="${errors.id }">ID나 email을 입력하세요.</c:if><br>
				</span>
				<span id="inputPw"> PW : <input type="password" name="password" placeholder="비밀번호를 입력해주세요" required="required" value="${param.pw }">
					<c:if test="${errors.password }">암호를 입력하세요.</c:if><br>
				</span>
					<c:if test="${errors.idOrPwNotMatch }"> 아이디와 암호가 일치하지 않습니다.</c:if><br>
				<button type="submit">로그인</button><br>
			</form>
					<a href="../forgot.do"><button>ID/PW</button></a>
					<a href="../join.do"><button>회원가입</button></a>
			
				<c:set var="clientId" value="188766d70b45863a165fa74d7d8a455b" />
				<c:set var="redirectUri" value="http://localhost:8080/BBS/login.do" />
			<br>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code">
					<button type="submit"><img src="./img/kakao_login.png"></button>
				</a>
			<br>
	
			
<!-- 
<a href="https://kauth.kakao.com/oauth/logout?client_id=${clientId}&logout_redirect_uri=${logout_redirectUri}"><button type="submit">카카오 로그아웃</button></a>
-->
		
		</c:if>	
		</div>
	</div>
		
		</div>
		<label for="popup">
		</label>
	</div>
	
	<label onclick="onMyPageClick();">마이페이지</label>
</div>

