<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰내용 읽기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link href="../css/articleWrite.css" rel="stylesheet">
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
h1{
	font-size:50px;
	text-align:center;
	color: white;
}
table{
	margin-left: auto; 
	margin-right: auto;
	border-top: 3px solid white; 
	border-bottom: 3px solid white; 
	height: auto;
	min-height: 400px;
	width:auto;
	min-width:800px;
	max-width:800px;
	color: white;
	border-collapse: collapse;
}
#nButton{
	margin-top: 10px;
	width: 45%; 
	margin-top:10px; 
	margin-right: auto; 
	margin-left: auto;
}
#nButton a{
	color: white;
}
#main {
	position: relative;
	float: right;
	width: calc(100% - 150px);
	height: calc(auto - 35px);
	background-color: #6e6e6e;
}

#mainWrapper {
	position: relative;
	left: 20px;
	width: calc(100% - 20px);
	height: auto;
	background-color: #2b2b2b;
}
</style>

<script type= "text/javascript">
$(document).ready(function() {
	var main = document.getElementById('main');
	var mainWrapper = document.getElementById('mainWrapper');
	var menu = document.getElementById('menu');
	
	var maxHeight;
	if (main.offsetHeight > mainWrapper.offsetHeight)
		maxHeight = main.offsetHeight;
	else
		maxHeight = mainWrapper.offsetHeight;
	
	maxHeight += 50;
	maxHeight += 'px';
	
	menu.style.height = maxHeight;
	main.style.height = maxHeight;
	mainWrapper.style.height = maxHeight;
});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/view/component/header.jsp" />
		</div>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		
		<div id="main">
		<div id="mainWrapper">
		<h1>Review</h1>
		
		<table width=auto;>

			<tr>
				<td style="height: 30px; min-height: 30px;">제목</td>
				<td style="margin-top: 10px; margin-bottom: 10px;">${reviewData.review.number}번글&nbsp;:&nbsp;<c:out value="${reviewData.review.title}" /></td>
			</tr>
			<tr>
				<td style="height: 30px; min-width: 70px;  min-height: 30px; margin-bottom: 10px;">작성자</td>
				<td>${reviewData.review.writer.name}</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 500px; border-top:3px solid white; text-align: center; min-height: 300px; height: 300px;"><c:out value="${reviewData.content}" /></td>
			</tr>
		</table>
			<div id="nButton"> 
				<dl style="text-align: center;"><c:set var="pageNo"
						value="${empty param.pageNo ? '1' : param.pageNo}" /> <a
					href="list.do?pageNo=${pageNo}">[목록]</a></dl> 
					
					<dl style="text-align: right; margin-bottom: 10px;">
					<c:if test="${authUser.id == reviewData.review.writer.id}">
						<a href="modify.do?no=${reviewData.review.number}">[리뷰수정]</a>&emsp;
						<a href="delete.do?no=${reviewData.review.number}">[리뷰삭제]</a>
					</c:if></dl>
			</div>
		<br> <h2 style="text-align: center; color: white;">[리뷰 코멘트]</h2>
		<div id="message">
			<c:import url="/WEB-INF/view/messagelist.jsp" />
		</div>
		</div>
		</div>
	</div>
</body>
</html>