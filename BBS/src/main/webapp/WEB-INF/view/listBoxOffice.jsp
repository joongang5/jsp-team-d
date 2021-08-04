<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
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
							<li>순위</li>
							<li>제목</li>
							<li>개봉일</li>
							<li>관객수</li>
						</ul>
					</li>
					<c:forEach var="boxOffice" items="${page.content }">
					<li>
						<ul>
							<li>${boxOffice.rank }</li>
							<li>${boxOffice.movieNm }</li>
							<li>${boxOffice.openDt }</li>
							<li>${boxOffice.audiAcc }</li>
						</ul>
					</li>						
					</c:forEach>
				</ul>
				<br />
				<div id="paging">
					<c:set var="command" value="list.do" scope="request" />
					<c:import url="/WEB-INF/view/component/paging.jsp" />
				</div>
				<c:if test="${sessionScope.authUser ne null }">
					<a href="write.do">글쓰기</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>