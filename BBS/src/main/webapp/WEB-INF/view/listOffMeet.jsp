<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화팟</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

table {
	margin: 0 auto;
	height: 400px;
	width: 1000px;
	background-color: yellow;
	border-collapse: collapse;
	text-align: center;
}

th {
	border-bottom: 5px solid black;
}

td {
	border-bottom: 5px solid black;
}

button {
	position: absolute;
	left: 77%;
}

#title {
	text-align: left;
	width: 250px;
}

tr:hover {
	background-color: green;
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
			<table>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
				<c:if test="${offmeetPage.hasNoOffMeet()}">
				<tr>
					<td colspan="4"> 게시글이 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach var="offmeet" items="${offmeetPage.content }">
				<tr>
					<td>${offmeet.number }</td>
					<td>
					<a href="read.do?no=${offmeet.number}&pageNo=${offmeetPage.currentPage }">
					<c:out value="${offmeet.title }"/>
					</a>
					</td>
					<td>${offmeet.writer.name }</td>
					<td>${offmeet.modifiedDate }</td>
					<td>${offmeet.readCount }</td>
				</c:forEach>
				<c:if test="${offmeetPage.hasOffMeet() }">
					<tr>
						<td colspan="7">
							<c:if test="${offmeetPage.startPage >5 }">
							<a href="list.do?pageNo=${offmeetPage.startPage-5 }">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin= "${offmeetPage.startPage }" end="${offmeetPage.endPage }">
							<a href="list.do?pageNo=${pNo }">[${pNo }]</a>
							</c:forEach>
							<c:if test="${offmeetPage.endPage < offmeetPage.totalPages }">
							<a href="list.do?pageNo=${offmeetPage.startPage +5 }">[다음]</a>
							</c:if>
						</td>
					</tr>
							
				</c:if>
			</table>
			<a href="write.do">
			<button>글쓰기</button>
			</a>
		</div>

	</div>


</body>
</html>