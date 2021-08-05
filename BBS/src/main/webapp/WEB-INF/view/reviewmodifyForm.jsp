<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰수정</title>
<style type="text/css">
body{
	background: black;
	color: white;	
	font-family: 'Nanum Myeongjo', serif;
}  
</style>
</head>
<body>
	<form action="modify.do" method="post">
		<input type="hidden" name="no" value="${modReq.reviewNumber}">
		<p>
			번호<br/>${modReq.reviewNumber}
		</p>
		<p>
			제목:<br/><input type="text" name="title" value="${modReq.title}">
			<c:if test="${errors.title}">리뷰제목을 입력하세요.</c:if>
		</p>
		<p>
			내용:<br/>
			<textarea name="content" rows="5" cols="30">${modReq.content}</textarea>
		</p>
		<input type="submit" value="수정완료">
	</form> 
</body>
</html>