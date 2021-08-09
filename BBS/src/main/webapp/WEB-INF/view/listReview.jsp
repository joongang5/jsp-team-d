<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 목록</title>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link href="../css/articleWrite.css" rel="stylesheet">
<link rel="stylesheet" href="../css/custom.min.css">

</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
	<br>제목을 클릭하면 내용을 볼 수 있습니다.
	<table border="1">
		<tr>
			<td colspan="4"><a href="../review/write.do">[리뷰쓰기]</a></td>
			
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
		</tr>
		<c:if test="${reviewPage.hasNoReviews()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="review" items="${reviewPage.content}">
			<tr>
				<td>${review.number }</td>
				<td><a
					href="read.do?no=${review.number}&pageNo=${reviewPage.currentPage}">
						<c:out value="${review.title}" />
				</a></td>
				<td>${review.writer.name}</td>
				<td>${review.readCount}</td>
			</tr>
		</c:forEach>
		<c:if test="${reviewPage.hasReviews()}">
			<tr>
				<td colspan="4">
				<c:if test="${reviewPage.startPage > 5}">
					<a href="list.do?pageNo=${reviewPage.startPage - 5}">[이전]</a>
					</c:if>
					<c:forEach var="pNo"
					begin="${reviewPage.startPage}" end="${reviewPage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach>
					<c:if test="${reviewPage.endPage < reviewPage.totalPages}">
					<a href="list.do?pageNo=${reviewPage.startPage +  5}">[다음]</a>
					</c:if>
					</td>
					</tr>
		</c:if>
	</table>
			<a href="../boxoffice/list.do">[영화홈으로]</a>
</body>
</html>