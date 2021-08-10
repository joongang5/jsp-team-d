<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내가 쓴 리뷰</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
	<div id="myReview">
		내가 쓴 리뷰
		<table border="1" >
		<tr>
			<td>번호</td>
			<td>${myReview.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${myReview.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value="${myReview.title}"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><c:out value="${myReview.content}"/></td>
		</tr>
	</table>
	</div>
</body>
</html>