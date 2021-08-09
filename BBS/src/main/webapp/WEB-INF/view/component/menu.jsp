<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<style type="text/css">
.naviSub {
	display: none;
}

.menuItem {
	display: block;
	padding: 5px 10px;
	border: 1px solid #2b2b2b;
	cursor: pointer;
}

.menuItem:hover {
	color: gray;
}

.menuItemSub {
	display: block;
	padding: 5px 20px;
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
	var naviId;

	var paths = curPath.split('/');
	if (paths.length > 2) {
		naviId = '#' + paths[2];
	}
	
	if (referrer.indexOf(curPath) > 0) {
		$(naviId).next('.naviSub').css('display', 'block');
	} else {
		$(naviId).next('.naviSub').slideDown('fast');
	}
	
	$(naviId).css('border', '1px solid white');
});
</script>

<div class="navi">
	<div class="menuItem" id="boxOffice" onclick="menuClick('boxOffice/list')">영화 홈</div>
	<div class="menuItem" id="movie" onclick="menuClick('movie/list')">상영작·예정작</div>
	<div class="naviSub">
		<div class="menuItemSub" onclick="menuClick('movie/list')">▷ 현재 상영영화</div>
		<div class="menuItemSub" onclick="menuClick('movie/list')">▷ 개봉 예정영화</div>
	</div>
	<div class="menuItem" id="login" onclick="menuClick('login')">로그인 페이지</div>
	<div class="menuItem" id="myPage" onclick="menuClick('myPage')">마이 페이지</div>
	<div class="menuItem" id="notice" onclick="menuClick('notice/list')">공지사항</div>
	<div class="menuItem" id="offmeet" onclick="menuClick('offmeet/list')">영화팟 찾기</div>
	<div class="menuItem" id="review" onclick="menuClick('review/list')">영화 리뷰/평가</div>
	<div class="naviSub">
		<div class="menuItemSub" onclick="menuClick('review/list')">리뷰</div>
		<div class="menuItemSub" onclick="menuClick('rating/rating')">평가 라운지</div>
	</div>
	
	<div class="menuItem" onclick="menuClick('admin')">관리자</div>
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
					<a href="join.do"><button>회원가입</button></a>
			
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