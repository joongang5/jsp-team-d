<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개봉 예정영화</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
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
	max-width: 900px;
	height: auto;
	min-height: 400px;
	boder-collapse: collapse;
	color:white;
}

#paging a {
	color: white;
}

#titleN th{
	border-bottom: 3px solid white;
	max-height: 30px;
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
				<h1>Reserved</h1>
				<table>
					<tr id="titleN" style="text-align: center; height: 35px;">
						<th style="width: 9%;">영화코드</th>
						<th style="width: 46%">제목</th>
						<th style="width: 11%;">제작국가</th>
						<th style="width: 25%;">장르</th>
						<th style="width: 9%;">개봉일</th>
					</tr>

					<c:if test="${!page.hasContent()}">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
									
					<c:forEach var="movie" items="${page.content}">
						<tr id="contentN">
							<td style="text-align: center;">${movie.movieCd }</td>
							
							<td style="text-overflow:ellipsis; white-space:nowrap; max-width:360px; overflow:hidden">
								<a style="color:white;" href="/BBS/movie/read.do?no=${movie.movieCd}"> ${movie.movieNm } </a>
							</td>
							
							<td style="text-align: center;">${movie.nationAlt}</td>
							<td style="text-align: center;">${movie.genreAlt}</td>
							<td style="text-align: center;">${movie.openDt}</td>
						</tr>
					</c:forEach>
				</table>
								
				<div id="paging" style="text-align: center; width: 45%; margin-top:10px; margin-right: auto; margin-left: auto;">
					<c:if test="${page.hasContent()}">
						<tr>
							<c:if test="${page.startPage > 5}">
								<a href="list.do?pageNo=${page.startPage - 5}">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin="${page.startPage}" end="${page.endPage}">
								<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
							</c:forEach>

							<c:if test="${page.endPage < page.totalPages}">
								<a href="list.do?pageNo=${page.startPage +  5}">[다음]</a>
							</c:if>
						</tr>
					</c:if>
					<br>
					<c:if test="${authUser.id == 'admin' }">
						<a style="float: right;" href="write.do">[공지사항 쓰기]</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>