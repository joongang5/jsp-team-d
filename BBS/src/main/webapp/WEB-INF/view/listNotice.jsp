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
	height: auto;
	min-height: 400px;
	boder-collapse: collapse;
	color:white;
}
#paging a{
	color: white;
}
#writeN a{
	float: right; 
	color: white;
}
#titleN th{
	border-bottom: 3px solid white;
}

</style>
</head>
<body>
	<div id="container" > 
		<div id="header">
			<c:import url="/WEB-INF/view/component/header.jsp" />
		</div>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<div id="mainWrapper">
				<h1>Notice</h1>
				<table >

					<tr id="titleN" style="text-align: center;"> 
						<th style="width: 7%;">번호</th>
						<th style="width: 44%">제목</th>
						<th style="width: 11%;">작성자</th>
						<th style="width: 29%;">날짜</th>
						<th style="width: 9%;">조회수</th>
					</tr>

					<c:if test="${noticePage.hasNoNotices()}">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>

					<c:forEach var="notice" items="${noticePage.content}">
						<tr id="contentN">
							<td style="text-align: center;">${notice.number }</td>
							
							<td>
							<a style="color:white;" href="read.do?no=${notice.number}&pageNo=${noticePage.currentPage}"> ${notice.title } </a>
							</td>
							
							<td style="text-align: center;">${notice.writer.name}</td>
							<td style="text-align: center;"><fmt:formatDate value="${notice.modifiedDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td style="text-align: center;">${notice.readCount}</td>
						</tr>
					</c:forEach>
				</table>


				<div id="paging" style="text-align: center;">
					<c:if test="${noticePage.hasNotices()}">
						<tr>
							<c:if test="${noticePage.startPage > 5}">
								<a href="list.do?pageNo=${noticePage.startPage - 5}">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin="${noticePage.startPage}"
								end="${noticePage.endPage}">
								<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach>

							<c:if test="${noticePage.endPage < noticePage.totalPages}">
								<a href="list.do?pageNo=${noticePage.startPage +  5}">[다음]</a>
							</c:if>
						</tr>
					</c:if>
				</div>

					<div id="writeN" >
						<a href="write.do">[공지사항 쓰기]</a>
					</div>
			</div>
		</div>
	</div>
</body>
</html>