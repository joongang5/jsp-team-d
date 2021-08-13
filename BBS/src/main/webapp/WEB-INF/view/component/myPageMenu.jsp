<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
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
	background-color: black;
	position: relative;
	width:150px;
	font-family: 'Nanum Gothic';
	
}

</style>
</head>
<body>
<div class="myPageList">
	<div class="menuItem" onclick="menuClick('myPage')">내 정보</div>
	<div class="menuItem" onclick="menuClick('myPage/reviewList')">내 리뷰</div>
	<div class="menuItem" onclick="menuClick('myPage/offMeetList')">내 영화팟 글</div>
</div>
</body>
</html>