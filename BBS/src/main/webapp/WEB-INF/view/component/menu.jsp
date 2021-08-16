<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath }/js/menu.js" type="text/javascript"></script>


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

<div class="navi">
	<div class="menuItem" id="boxOffice" onclick="menuClick('boxOffice/list')">영화 홈</div>
	<div class="menuItem" id="movie" onclick="menuClick('movie/list')">상영작·예정작</div>
	<div class="naviSub">
		<div class="menuItemSub" id="movieSub" onclick="menuClick('movie/list')"> 
			<label>▷ </label>
			<label>현재 상영영화</label> 
		</div>
		<div class="menuItemSub" id="reserved" onclick="menuClick('movie/reserved/list')">
			<label>▷ </label>
			<label>개봉 예정영화</label> 
		</div>
	</div>
	<div class="menuItem" id="notice" onclick="menuClick('notice/list')">공지사항</div>
	<div class="menuItem" id="offmeet" onclick="menuClick('offmeet/list')">영화팟 찾기</div>
	<div class="menuItem" id="review" onclick="menuClick('review/list')">영화 리뷰/평가</div>
	<div class="naviSub">
		<div class="menuItemSub" id="reviewSub" onclick="menuClick('review/list')">
			<label>▷ </label>
			<label>리뷰</label>
		</div>
		<div class="menuItemSub" onclick="menuClick('rating/rating')">
			<label>▷ </label>
			<label>평가 라운지</label>
		</div>
	</div>
	<div class="menuItem" onclick="menuClick('admin')">관리자</div>
	
	
</div>





