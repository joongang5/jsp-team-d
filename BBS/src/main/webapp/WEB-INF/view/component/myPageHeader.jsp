<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
$(document).ready(function() {
	var curPath = $(location).attr('pathname');
	var split = curPath.split('/');
	var naviId = '';
	if (split.length > 2)
		naviId = split[2];
	
	if (naviId == '' || naviId == 'boxOffice') {
		$('#headerButtons').css('right', '');
		$('#headerButtons').css('width', '500px');
		$('#headerButtons').css('left', '460px');	
		$('.modalBox').css('margin-top', '0px');	
	}
});

function onHomeClick() {
	location.href = '/BBS/boxOffice/list.do';
}
</script>
<style type="text/css">
.home {
	position: relative;
	width: 146px;
	height: 31px;
	border: 2px solid #2b2b2b;
	font-weight: bold;
	color: white;
	background-color: black;
	cursor: pointer;
}

.home>div {
	position: absolute;
	top: 50%;
	left: 45%;
	transform: translate(-50%, -50%);
	cursor: pointer;
}

</style>

<div class="home">
	<div onclick="onHomeClick();">D'movie</div>
</div>