<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
<style type="text/css">
body{
	margin: 0 auto;
	padding: 0;
	width:auto;
	height: auto;
	min-height: auto;
	background-color: black;
}
#head{
	font-size: 50px;
	font-weight: bolder;
	text-align: center;
	color: white;
}
#container{
	height: 90px;
	border-bottom: 1px solid gray;
	border-top: 1px solid gray;
}
#content{
	height: auto;
	min-height: 500px;
	text-align: center;
	border-bottom: 1px solid gray;
}
#backlist{
	float: right;
	margin-top: 10px;
}
#backlist button{
	border-radius: 10px; 
	height: 50px;
	width: 100px; 
	color: white; 
	background-color: gray;
}
#backlist button:hover{
	color: white;
	background-color: black;
}
#menu{
	width: 120px;
	height: auto;
	background-color: white;
}
</style>
</head>
<body>
<div id="menu">
	<c:import url="component/menu.jsp"/>
</div>
	<table id=container style="width: 100%;">
		<h1 id=head>Notice</h1>
		
		<tr id=title>
			<td style="width: 10%; height: 30px; text-align: center; color: white;">제목</td>
			<td style="color: white;">${noticeData.notice.ntitle }</td>
		</tr>
		<tr id=writer>
			<td style="width: 10%; height: 30px; text-align: center; color: white;">작성자</td>
			<td style="color: white;">${noticeData.notice.id }</td>
		</tr>
		<tr id=daycount style=" height: 30px;">
			<td style="width: 10%; text-align: center; color: white;">날짜</td>
			<td style="width: 70%; color: white;">${noticeData.notice.ndate }</td>
			<td style="width: 10%; text-align: center; color: white;">조회수</td>
			<td style="width: 10%; text-align: center; color: white;">${noticeData.notice.ncount }</td>
		</tr>
	</table>	
		
	<table id=content style="width: 100%; color: white;">
		<tr>	
			<td>${noticeData.notice.ncontent }</td>
		</tr>
	</table>

	
	<div id=backlist>
		<a href='/BBS/notice/list.do'><button>목록으로</button></a>
		<a href='/BBS/notice/modify.do'><button>수정하기</button></a>
	</div>

</body>
</html>