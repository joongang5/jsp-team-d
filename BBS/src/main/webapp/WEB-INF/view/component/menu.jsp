<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style type="text/css">
input[id*="menu"] {
	display: none;
}

.naviSub {
	display: none;
}

.menuItem {
	display: block;
	padding: 5px;
	border: 1px solid gray;
	cursor: pointer;
}

.menuItem:hover {
	color: gray;
}

.menuItemSub {
	display: block;
	padding: 5px 10px;
	font-size: 12px;
	cursor: pointer;
}

.menuItemSub:hover {
	color: gray;
}
</style>

<script>
$(function() {
	var curPath = $(location).attr('pathname');
	var referrer = document.referrer;
	var naviSubId;

	var paths = curPath.split('/');
	if (paths.length > 2) {
		naviSubId = '#' + paths[2];
	}
	
	if (referrer.indexOf(curPath) > 0) {
		$(naviSubId).css('display', 'block');
	} else {
		$(naviSubId).slideDown('fast');
	}
});
</script>

<div class="navi">
	<input type="radio" name="navi" id="menu01">
	<label for="menu01" class="menuItem" onclick="menuClick('boxOffice/list')">영화 홈</label>
	
	<input type="radio" name="navi" id="menu02">
	<label for="menu02" class="menuItem" onclick="menuClick('movie/list')">상영작·예정작</label>
	<div class="naviSub" id="movie">
		<input type="radio" name="naviSub01" id="menuSub01">
		<label for="menuSub01" class="menuItemSub" onclick="menuClick('movie/list')">▷ 현재 상영영화</label>
		<input type="radio" name="naviSub01" id="menuSub02">
		<label for="menuSub02" class="menuItemSub" onclick="menuClick('movie/list')">▷ 개봉 예정영화</label>
	</div>
	
	<input type="radio" name="navi" id="menu03">
	<label for="menu03" class="menuItem" onclick="menuClick('login')">로그인 페이지</label>
	
	<input type="radio" name="navi" id="menu04">	
	<label for="menu04" class="menuItem" onclick="menuClick('myPage')">마이 페이지</label>
	
	<input type="radio" name="navi" id="menu05">
	<label for="menu05" class="menuItem" onclick="menuClick('notice/list')">공지사항</label>
	
	<input type="radio" name="navi" id="menu06">
	<label for="menu06" class="menuItem" onclick="menuClick('offmeet/list')">영화팟 찾기</label>
	
	<input type="radio" name="navi" id="menu07">
	<label for="menu07" class="menuItem" onclick="menuClick('review/list')">영화 리뷰/평가</label>
	<div class="naviSub" id="review">
		<input type="radio" name="naviSub07" id="menuSub02">
		<label for="menuSub02" class="menuItemSub" onclick="menuClick('review/list')">리뷰</label>
		<input type="radio" name="naviSub07" id="menuSub01">
		<label for="menuSub01" class="menuItemSub" onclick="menuClick('rating/rating')">평가 라운지</label>
	</div>
	
	<input type="radio" name="navi" id="menu08">
	<label for="menu08" class="menuItem" onclick="menuClick('admin')">관리자</label>
</div>

<div id="menuLoginBox">
<br>
<c:if test="${! empty authUser}">
			
			${authUser.name}님<br>
			<a href="logout.do"><button>로그아웃</button></a>
			<a href="myPage.do"><button>마이페이지</button></a>
			
		</c:if>

<c:if test="${empty authUser}">
			
			<form action="login.do" method="post" onsubmit="">
				<span id="inputId"> ID : <input type="text" placeholder="아이디를 입력해주세요" name="id" value="${param.id }">
					<c:if test="${errors.id }">ID나 email을 입력하세요.</c:if><br>
				</span>
				<span id="inputPw"> PW : <input type="password" name="password" placeholder="비밀번호를 입력해주세요" value="${param.pw }">
					<c:if test="${errors.password }">암호를 입력하세요.</c:if><br>
				</span>
					<c:if test="${errors.idOrPwNotMatch }"> 아이디와 암호가 일치하지 않습니다.</c:if><br>
				<button type="submit">로그인</button>
			</form>
					<a href="./find"><button>ID/PW</button></a>
					<a href="join.do">회원가입</a>
			
				<c:set var="clientId" value="188766d70b45863a165fa74d7d8a455b" />
				<c:set var="redirectUri" value="http://localhost:8080/BBS/login.do" />
			<br>
				<a href="https://kauth.kakao.com/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code">
					<button type="submit">카카오 로그인</button>
				</a>
			<br>
			
		</c:if>
</div>

<script type="text/javascript" src="/BBS/js/menu.js"></script>