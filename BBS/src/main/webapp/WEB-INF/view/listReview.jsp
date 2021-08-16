<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/articleWrite.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/custom.min.css" rel="stylesheet" >
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
h1 {
	font-size: 50px;
	text-align: center;
	color: white;
}
table {
	margin-left: auto;
	margin-right: auto;
	border-top: 3px solid white;
	border-bottom: 3px solid white;
	width: auto;
	min-width:800px;
	height: auto;
	min-height: 400px;
	boder-collapse: collapse;
	color:white;
}
#titleN th{
	border-bottom: 3px solid white;
	max-height: 30px;
}
#paging a{
	color: white;
}

</style>
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
		<div id="mainWrapper" style="width: 100%; margin:0;">
		
		<h1>Review</h1>
		<table >
		
				
			<tr id="titleN" style="text-align: center;	height: 35px;"> 
				<th style="width: 9%;">번호</th>
				<th style="width: 71%">제목</th>
				<th style="width: 11%;">작성자</th>
				<th style="width: 9%;">조회수</th>
			</tr>
					
			<c:if test="${reviewPage.hasNoReviews()}">
				<tr>
					<td colspan="4">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="review" items="${reviewPage.content}">
				<tr>
					<td style="text-align: center;">${review.number }</td>
					
					<td style="text-overflow:ellipsis; white-space:nowrap; max-width:360px; overflow:hidden">
						<a style="color:white; " href="read.do?no=${review.number}&pageNo=${reviewPage.currentPage}">
							<c:out value="${review.title}" />
						</a>
					</td>
					<td style="text-align: center;">${review.writer.name}</td>
					<td style="text-align: center;">${review.readCount}</td>
				</tr>
			</c:forEach>
		</table>
		
		<div id="paging" style="text-align: center; width: 45%; margin-top:10px; margin-right: auto; margin-left: auto;">
			<c:if test="${reviewPage.hasReviews()}">
				<tr>
					<td colspan="4">
						<c:if test="${reviewPage.startPage > 5}">
							<a href="list.do?pageNo=${reviewPage.startPage - 5}">[이전]</a>
						</c:if> 
						<c:forEach var="pNo" begin="${reviewPage.startPage}" end="${reviewPage.endPage}">
							<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
						</c:forEach>
						<c:if test="${reviewPage.endPage < reviewPage.totalPages}">
							<a href="list.do?pageNo=${reviewPage.startPage +  5}">[다음]</a>
						</c:if>
					</td>
				</tr>
			</c:if>
			<br>
				<a style="float: right;" href="../review/write.do">[리뷰쓰기]</a>
		</div>
		</div>
		</div>
		</div>
</body>
</html>