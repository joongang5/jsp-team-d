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
			<h1>박스오피스</h1>
			<div id="mainWrapper">
				<ul id="ulTable">
					<li>
						<ul>
							<li>순위</li>
							<li>제목</li>
							<li>포스터</li>
						</ul>
					</li>
					<c:forEach var="boxOfficeView" items="${page.content }">
					<li>
						<ul>
							<li>${boxOfficeView.rank }</li>
							<li>${boxOfficeView.movieNm }</li>
							<li><img SRC="${boxOfficeView.image }" width="100" height="100"></li>
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