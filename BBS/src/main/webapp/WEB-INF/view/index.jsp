<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/main.css" rel="stylesheet">
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<h1>BoardList</h1>
			<div id="mainWrapper">
				<ul id="ulTable">
					<li>
						<ul>
							<li>No</li>
							<li>Title</li>
							<li>Writer</li>
							<li>Date</li>
							<li>Count</li>
						</ul>
					</li>
					<c:forEach var="article" items="${articlePage.content }">
					<li>
						<ul>
							<li>${article.no }</li>
							<li>${article.title }</li>
							<li>${article.writer.id }</li>
							<li>${article.regDate }</li>
							<li>${article.readCnt }</li>
						</ul>
					</li>						
					</c:forEach>
				</ul>
				<br />
				<div id="paging">
					<c:set var="command" value="index.do" scope="request" />
					<c:import url="/WEB-INF/view/component/paging.jsp" />
				</div>
				<c:if test="${sessionScope.authUser ne null }">
					<a href="article/write.do">글쓰기</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>