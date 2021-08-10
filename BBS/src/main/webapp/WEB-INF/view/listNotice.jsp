<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet">
<style type="text/css">
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
	height: 400px;
	min-height: 400px;
	color: white;
}

#titleN {
	
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
			<div id="mainWrapper">
				<h1>Notice</h1>
				<table border="1">

					<tr id="titleN" style="text-align: center;">
						<td style="width: 7%;">번호</td>
						<td style="width: 44%">제목</td>
						<td style="width: 11%;">작성자</td>
						<td style="width: 29%;">날짜</td>
						<td style="width: 9%;">조회수</td>
					</tr>


		<c:if test="${noticePage.hasNoNotices()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		
		<c:forEach var="notice" items="${noticePage.content}">
			<tr>
				<td style="text-align: center;">${notice.number }</td>
				<td style="color: white;"> 
					<a href="read.do?no=${notice.number}&pageNo=${noticePage.currentPage}">
						<c:out value="${notice.title}" />
					</a>
				</td>
				<td style="text-align: center;">${notice.writer.name}</td>
				<td style="text-align: center;"><fmt:formatDate value="${notice.modifiedDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
				<td style="text-align: center;">${notice.readCount}</td>
			</tr>
		</c:forEach>
	</table>
	
	
				<div id="paging" style="text-align: center; color: white;"> 
					<c:if test="${noticePage.hasNotices()}">
						<tr>
							<c:if test="${noticePage.startPage > 5}">
								<a href="list.do?pageNo=${noticePage.startPage - 5}">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin="${noticePage.startPage}" end="${noticePage.endPage}">
								<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach>

							<c:if test="${noticePage.endPage < noticePage.totalPages}">
								<a href="list.do?pageNo=${noticePage.startPage +  5}">[다음]</a>
							</c:if>
						</tr>
					</c:if>
				</div>

				<div id="writeN" style="float: right; color: white;">
					<a href="write.do">[공지사항 쓰기]</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>