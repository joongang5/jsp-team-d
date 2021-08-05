<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
<link href="./css/review.css" rel="stylesheet">
<style type="text/css">
body{
	background: black;
	color: white;	
	font-family: 'Nanum Myeongjo', serif;
}

</style>
</head>
<body>
	[리뷰게시판]
	<table border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>${reviewData.review.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${reviewData.review.writer.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value="${reviewData.review.title}"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><c:out value="${reviewData.content}"/></td>
		</tr>
		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}"/>
			<a href="list.do?pageNo=${pageNo}">[목록]</a>
				<c:if test="${authUser.id == reviewData.review.writer.id}">
					<a href="modify.do?no=${reviewData.review.number}">[리뷰수정]</a>
					<a href="delete.do?no=${reviewData.review.number}">[리뷰삭제]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>