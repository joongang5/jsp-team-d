<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
<style type="text/css">
body{
	margin: 0 auto;
	padding: 0;
	background-color: black;
}
#container{
	width: 1000px;
	height: auto;
	min-height: 500px;
	margin: 0 auto;
	padding: 0;
}
#head{
	font-weight: bold;
	font-size: 50px;
	text-align: center; 
	color: white;
}
#title, #writer, #daycount{ 
	height: 30px;
	text-align: center;
	color: white;
} 
#content{
	height: 100%;
	min-height: 410px;
	background-color: yellow;
	text-align: center;
	color: white;
	background-color: silver;
}

 
</style>
</head>
<body>
	<table id=container>
		<h1 id=head>Notice</h1>
		<tr id=title>
			<td style="width: 10%">제목</td>
			<td>${notice.ntitle }</td>
		</tr>
		<tr id=writer>
			<td style="width: 10%">작성자</td>
			<td>${notice.id }</td>
		</tr>
		<tr id=daycount>
			<td style="width: 10%">날짜</td>
			<td>${notice.ndate }</td>
			<td style="width: 10%">조회수</td>
			<td>${notice.ncount }</td>
		</tr>
		<tr>	
			<td id=content style="width: 1000px;">${notice.ncontent }내용</td>
		</tr>
		
<%-- 		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<c:if test="${authUser.id ==noticeData.notice.id }">
					<a href="modify.do?no=${noticeData.notice.nno }">[게시글수정]</a>
					<a href="delete.do?no=${noticeData.notice.nno }">[게시글삭제]</a>
				</c:if>
			</td>
		</tr> --%>
	</table>
</body>
</html>