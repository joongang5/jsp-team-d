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

.menuItemSub {
	display: block;
	padding: 5px;
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
		var paths = curPath.split('/');
		if (paths.length > 2) {
			var naviSubId = "#" + paths[2];
			$(naviSubId).slideDown('fast');
		}
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
		<label for="menuSub01" class="menuItemSub" onclick="menuClick('movie/list')">현재 상영영화</label>
		<input type="radio" name="naviSub01" id="menuSub02">
		<label for="menuSub02" class="menuItemSub" onclick="menuClick('movie/list')">개봉 예정영화</label>
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
	<label for="menu07" class="menuItem" onclick="menuClick('review/list')">영화 토론/리뷰</label>
	<div class="naviSub" id="review">
		<input type="radio" name="naviSub07" id="menuSub01">
		<label for="menuSub01" class="menuItemSub" onclick="menuClick('review/discussion')">토론</label>
		<input type="radio" name="naviSub07" id="menuSub02">
		<label for="menuSub02" class="menuItemSub" onclick="menuClick('review/review')">리뷰</label>
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
</div>

<script type="text/javascript" src="/BBS/js/menu.js"></script>