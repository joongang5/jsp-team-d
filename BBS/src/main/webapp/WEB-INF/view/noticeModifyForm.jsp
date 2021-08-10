<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
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
	<form action="modify.do" method="post">
		<input type="hidden" name="no" value="${modReq.noticeNumber}">
		
		<div id="write">
		<p>
			번호 : ${modReq.noticeNumber }번글<br>
	
			제목:<br/><input type="text" id="title" name="title" value="${modReq.title }">
			<c:if test="${errors.title }">제목을 입력하세요.</c:if>
		</p>
		<p>
			내용:<br/>
			<textarea id="content" name="content" rows="10" >${modReq.content }</textarea>
		</p>
		
			<p><input type="file" name="file" accept=".gif, .png, .jpg"></p>
		<input type="submit" value="글 수정">
		</div>
		
	</form> 
</body>
</html>