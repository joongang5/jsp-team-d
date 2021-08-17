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
<style type="text/css">
body{
	background-color: #2b2b2b; 
}
#write{
	text-align: center;

}
#title{
	width: 400px; 
}
#content{
	width: 400px;
}
</style>
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
					<br>
						<input id="title" type="text" name="title" required="required" placeholder="리뷰제목을 적어주세요">
						<c:if test="${errors.title }">제목을 입력하세요</c:if>
						<br>
						<br>
						<textarea id="content" name="content" rows="10" required="required"></textarea>
						<br>
						<input type="file" name="file1">
						<br>
						<br>
						<button type="submit">리뷰등록</button>
					</form>
					<br />
					<p onclick="location.href='/BBS/review/list.do'">리뷰 목록으로</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>