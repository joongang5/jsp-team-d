<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화팟</title>
<link href="../css/main.css" rel="stylesheet">

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

table {
	margin: 0 auto;
	height: 400px;
	width: 500px;
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

			</table>
			<button>글쓰기</button>
		</div>

	</div>


</body>
</html>