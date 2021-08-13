<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상영작·예정작</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/movieList.css" rel="stylesheet">
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
				<ul id="ulTable">
					<li>
						<ul>
							<li>제작국가</li>
							<li>제목</li>
							<li>장르</li>
							<li>개봉일</li>
						</ul>
					</li>
					<c:forEach var="movie" items="${page.content }">
					<li>
						<ul>
							<li>${movie.nationAlt }</li>
							<li>${movie.movieNm }</li>
							<li>${movie.genreAlt }</li>
							<li>${movie.openDt }</li>
						</ul>
					</li>						
					</c:forEach>
				</ul>
				<br />
				<div id="paging">
					<c:set var="command" value="list.do" scope="request" />
					<c:import url="/WEB-INF/view/component/paging.jsp" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>