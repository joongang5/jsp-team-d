<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰수정</title>
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
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		
	<form action="modify.do" method="post">
		<input type="hidden" name="no" value="${modReq.reviewNumber}">
		
		<div id="write">
		<p style="color: white;">
			번호<br/>${modReq.reviewNumber}
		</p>
		<p style="color: white;">
			제목:<br/><input type="text" id="title" name="title" value="${modReq.title}">
			<c:if test="${errors.title}">리뷰제목을 입력하세요.</c:if>
		</p>
		<p style="color: white;">
			내용:<br/>
			<textarea id="content" name="content" rows="10" cols="30">${modReq.content}</textarea>
		</p>
		<input type="submit" value="수정완료">
		</div>
	</form> 
</body>
</html>