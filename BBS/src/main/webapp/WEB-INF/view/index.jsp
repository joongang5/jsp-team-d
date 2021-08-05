<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/main.css" rel="stylesheet">
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<h1>Home</h1>

			<!-- 로그인, 로그아웃 관련 기능 / 이강민 -->
			<c:if test="${! empty authUser}">
				<a href="logout.do"><button>로그아웃</button></a>
				<a href="http://developers.kakao.com/logout">카카오 로그아웃</a>
				<a href="changePw.do"><button>암호변경</button></a>
			</c:if>
			<c:if test="${empty authUser}">
				<a href="join.do"><button>회원가입</button></a>
				<a href="login.do"><button>로그인</button></a>

				<c:set var="clientId" value="188766d70b45863a165fa74d7d8a455b"/>
				<c:set var="redirectUri" value="http://localhost:8080/BBS/kakaoHandler"/>
				
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code">
					<button type="submit">카카오 로그인</button>
				</a>
				
			</c:if>
			
		</div>
	</div>
</body>
</html>