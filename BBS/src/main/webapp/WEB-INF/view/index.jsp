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

				<c:set var="clientId" value="188766d70b45863a165fa74d7d8a455b"></c:set>
				<c:set var="redirectUri"
					value="http://172.30.1.48:8080/BBS/oauth/kakao.do"></c:set>
				<a
					href="https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code">
					<button>카카오 로그인</button>
				</a>
			</c:if>
			<button id="kakaoLogin" onclick="kakaoLogin()" >kakaoLogin</button>
			<button id="kakaoLogout" onclick="kakaoLogout()">kakaoLogout</button>
			<input id="userinfo" value="" />
		
			<script>
				// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
				Kakao.init('2d52ddc31775dc1f031f8163183ae794')
				// SDK 초기화 여부를 판단합니다.
				console.log(Kakao.isInitialized())
			</script>
			<script>
				function kakaoLogin() {
					//로그인하고
					Kakao.Auth.login({
						success : function(response) {
							//사용자 정보 가져오기
							Kakao.API.request({
								url : '/v2/user/me', //계정 정보를 가져오는 request url
								success : function(response) {
									let user = response.kakao_account //카카오 계정 정보
									console.log(user)
									user.host = 'kakao' //다른 로그인 서비스와 구분하기 위해서 개인적으로 추가했음
									// 해당 페이지에서 객체를 만들고 곧바로 user 정보를 사용할 수 도 있고,
									// input 엘리먼트에 json으로 저장해뒀다가 나중에 사용할 수도 있음. 여기서는 input에 저장
									const userinfo = document
											.querySelector('#userinfo')
									if (userinfo)
										userinfo.value = JSON.stringify(user) //user를 json문자열로 변환해서 저장해두기
								},
								fail : function(error) {
									console.log(error)
								},
							})
						},
						fail : function(error) {
							console.log(error)
						},
					})
					
					
				}
				function kakaoLogout() {
					if (Kakao.Auth.getAccessToken()) {
						//토큰이 있으면
						Kakao.API.request({
							//로그아웃하고
							url : '/v1/user/unlink',
							success : function(response) {
								//console.log(response)
							},
							fail : function(error) {
								console.log(error)
							},
						})
						//토큰도 삭제
						Kakao.Auth.setAccessToken(undefined)
						//유저정보도 삭제
						const userinfoElem = document
								.querySelector('#userinfo')
						if (userinfoElem)
							userinfoElem.value = ''
					}
				}
			</script>

		</div>
	</div>
</body>
</html>