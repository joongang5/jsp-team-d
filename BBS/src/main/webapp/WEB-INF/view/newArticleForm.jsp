<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link href="../css/articleWrite.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<div id="mainWrapper">
				<div id="write">
					<form action="write.do" method="post">
						<input type="text" name="title" required="required" placeholder="제목을 적어주세요">
						<c:if test="${errors.title }">제목을 입력하세요.</c:if>
						<textarea name="content" required="required"></textarea>
						<input type="file" name="file1">
						<button type="submit">글쓰기</button>
					</form>
					<br />
					<p onclick="location.href='/BBS/index.do'">게시판으로</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>