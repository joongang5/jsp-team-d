<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰내용 읽기</title>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link href="../css/articleWrite.css" rel="stylesheet">

</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
	<br>
	[리뷰게시판입니다.]
	<table border="1" width= auto;>
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
	<br>
	[리뷰 코멘트]
		<div id="message">
			<c:import url="/WEB-INF/view/messagelist.jsp" />
		</div>
</body>
</html>