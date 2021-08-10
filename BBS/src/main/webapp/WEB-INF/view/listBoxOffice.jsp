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
		<div id="header">
			<c:import url="/WEB-INF/view/component/header.jsp" />
		</div>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<div id="mainWrapper">
				<div id="movieMain">
					<div>박스오피스</div>
					<table id="row01">
						<tr>
							<c:forEach var="boxOfficeView" items="${page.content }" begin="0" end="4" step="1">
								<td class="imageTd">
									<img SRC="${boxOfficeView.image }" width="100" height="130">
									<div>${boxOfficeView.rank }</div>
								</td>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach var="boxOfficeView" items="${page.content }" begin="0" end="4" step="1">
								<td class="labelTd">
									<div>
										<label>누적관객</label>
										<label>${boxOfficeView.audiAcc }명</label>
									</div>
								</td>
							</c:forEach>
						</tr>
					</table>
					<table id="row02">
						<tr>
							<c:forEach var="boxOfficeView" items="${page.content }" begin="5" end="9" step="1">
								<td class="imageTd">
									<img SRC="${boxOfficeView.image }" width="100" height="130">
									<div>${boxOfficeView.rank }</div>
								</td>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach var="boxOfficeView" items="${page.content }" begin="5" end="9" step="1">
								<td class="labelTd">
									<div>
										<label>누적관객</label>
										<label>${boxOfficeView.audiAcc }명</label>
									</div>
								</td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>