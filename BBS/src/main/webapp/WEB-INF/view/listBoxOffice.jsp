<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 홈</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/jquery.scrollto@2.1.3/jquery.scrollTo.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/boxOffice.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/scrollTo.js" type="text/javascript" ></script>
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
			<div id="mainWrapper" style="width: 800px">
				<div id="movieMain">
					<a href="#pageBoxOffice" class="panel selected">박스오피스</a>
					<a href="#pageReservedMovie" class="panel">개봉예정작</a>
					<div id="wrapper">
						<div id="mask">
							<div id="pageBoxOffice" class="scrollItem">
								<div class="scrollContent">
									<table id="row01">
										<tr>
											<c:forEach var="boxOfficeView" items="${boxOfficePage.content }"
												begin="0" end="4" step="1">
												<td class="imageTd">
													<img SRC="${boxOfficeView.image }">
													<div>${boxOfficeView.rank }</div>
												</td>
											</c:forEach>
										</tr>
										<tr>
											<c:forEach var="boxOfficeView" items="${boxOfficePage.content }"
												begin="0" end="4" step="1">
												<td class="labelTd">
													<div>
														<label>누적관객</label> <label>${boxOfficeView.audiAcc }명</label>
													</div>
												</td>
											</c:forEach>
										</tr>
									</table>
									<table id="row02">
										<tr>
											<c:forEach var="boxOfficeView" items="${boxOfficePage.content }"
												begin="5" end="9" step="1">
												<td class="imageTd"><img SRC="${boxOfficeView.image }">
													<div>${boxOfficeView.rank }</div></td>
											</c:forEach>
										</tr>
										<tr>
											<c:forEach var="boxOfficeView" items="${boxOfficePage.content }"
												begin="5" end="9" step="1">
												<td class="labelTd">
													<div>
														<label>누적관객</label> <label>${boxOfficeView.audiAcc }명</label>
													</div>
												</td>
											</c:forEach>
										</tr>
									</table>
								</div>
							</div>
							<div id="pageReservedMovie" class="scrollItem">
								<div class="scrollContent">
									<table id="row01">
										<tr>
											<c:forEach var="reservedMovieView" items="${reservedMoviePage.content }"
												begin="0" end="4" step="1">
												<td class="imageTd"><img SRC="${reservedMovieView.image }">
											</c:forEach>
										</tr>
										<tr>
											<c:forEach var="reservedMovieView" items="${reservedMoviePage.content }"
												begin="0" end="4" step="1">
												<td class="labelTd">
													<div>
														<label>${reservedMovieView.openDt }</label> <label>개봉</label>
													</div>
												</td>
											</c:forEach>
										</tr>
									</table>
									<table id="row02">
										<tr>
											<c:forEach var="reservedMovieView" items="${reservedMoviePage.content }"
												begin="5" end="9" step="1">
												<td class="imageTd"><img SRC="${reservedMovieView.image }">
											</c:forEach>
										</tr>
										<tr>
											<c:forEach var="reservedMovieView" items="${reservedMoviePage.content }"
												begin="5" end="9" step="1">
												<td class="labelTd">
													<div>
														<label>${reservedMovieView.openDt }</label> <label>개봉</label>
													</div>
												</td>
											</c:forEach>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>