<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<div class="menuItem" onclick="menuClick('myPage')">�� ����</div>
	<div class="menuItem" onclick="menuClick('myPage/reviewList')">�� ����</div>
	<div class="menuItem" onclick="menuClick('myPage/offMeetList')">�� ��ȭ�� ��</div>
</div>
