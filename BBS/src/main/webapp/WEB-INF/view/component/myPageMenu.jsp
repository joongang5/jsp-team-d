<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="${pageContext.request.contextPath }/js/menu.js" type="text/javascript"></script>

<style type="text/css">
.menuItem {
	display: block;
	padding: 5px 10px;
	border: 1px solid #2b2b2b;
	cursor: pointer;
	
}
.menuItem:hover {
	color: gray;
}
.myPageList{
	float:left;
	background-color: black;
	position: relative;
	width:150px;
	height: 820px;
	color: white;
}
</style>

<div class="myPageList">
	<div class="menuItem" onclick="menuClick('myPage')">내 정보</div>
	<div class="menuItem" onclick="menuClick('myPage/reviewList')">내 리뷰</div>
	<div class="menuItem" onclick="menuClick('myPage/offMeetList')">내 영화팟 글</div>
</div>
