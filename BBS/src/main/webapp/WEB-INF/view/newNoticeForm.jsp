<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<link href="../css/main.css" rel="stylesheet">
<style type="text/css">
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
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>
	
	<form action="write.do" method="post">
	
	<div id="write">
	<p>
		제목:<br/><input type="text" id="title" name="title" value="${param.title }">
		<c:if test="${errors.title }">제목을 입력하세요.</c:if>
	</p>
	<p>
		내용:<br/>
		<textarea id="content" name="content" rows="10" ></textarea>
	</p>
	<p><input type="file" name="file" accept=".gif, .png, .jpg"></p>
<input type="submit" value="새 글 등록">
	</div>
</form>
</body>
</html>