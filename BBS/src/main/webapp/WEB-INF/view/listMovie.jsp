<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/boxOffice.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<h1>최신 영화 100</h1>
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