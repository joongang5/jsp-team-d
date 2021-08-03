<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style type="text/css">
body{
	margin: 0 auto;
	padding: 0;
	width: 1000px;
	height: 1000px;
	background-color: black;
}
#title{
	font-weight:bolder;
	background-color: gray;
}
table{
	width: 100%;
	text-align: center;
	height: 500px;
	border-collapse: collapse;
	color: white;
}
table, tr{
	border: 1px solid white;	
}
#content{
	
}
</style>
</head>
<body>
	<table id=table>
		<tr id=title style="height: 9%">
			<td style="width: 5%">번호</td>
			<td style="width: auto">제목</td>
			<td style="width: 13%">작성자(아이디)</td>
			<td style="width: 20%">날짜</td>
			<td style="width: 7%">조회수</td>
		</tr>
	<c:forEach var="notice" items="${list }">
			<tr id=content style="height: 91%">
				<td>${notice.nno }</td>
				<td><a href="./read.do?nno=${notice.nno }">${notice.ntitle }</a></td>
				<td>${notice.name} (${notice.id })</td>
				<td>${notice.ndate}</td>
				<td>${notice.ncount }</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>