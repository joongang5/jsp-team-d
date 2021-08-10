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
</head>
<body>
	<div id="container">
	<div id="loginBox">
		<!-- 로그인, 로그아웃 관련 기능 / 이강민 -->
		<c:if test="${! empty authUser}">
			
			<h1>${authUser.name}님 로그인 되었습니다.</h1>
				<a href="logout.do"><button>로그아웃</button></a>
			<c:if test="${empty snsAuthUser}">
			<!-- sns 로그인 사용자들만 암호변경 사용불가 -->
				<a href="changePw.do"><button>암호변경</button></a>
			</c:if>
		</c:if>
			
		<c:if test="${empty authUser}">
			
			<h1>로그인 해주세요</h1>

			<form action="login.do" method="post" onsubmit="">
				<span id="inputId"> ID : <input type="text" placeholder="아이디를 입력해주세요" name="id" value="${param.id }">
					<c:if test="${errors.id }">ID나 email을 입력하세요.</c:if><br>
				</span>
				<span id="inputPw"> PW : <input type="password" name="password" placeholder="비밀번호를 입력해주세요" value="${param.pw }">
					<c:if test="${errors.password }">암호를 입력하세요.</c:if><br>
				</span>
					<c:if test="${errors.idOrPwNotMatch }"> 아이디와 암호가 일치하지 않습니다.</c:if><br>
				<button type="submit">로그인</button><br>
			</form>
					<a href="./find"><button>ID/PW</button></a>
					<a href="join.do"><button>회원가입</button></a>
			
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
</body>
</html>