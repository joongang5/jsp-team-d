<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	리뷰 수정완료
	<br>
	<!-- ${ctxPath = pageContext.request.contextPath} -->
	<a href="${ctxPath }/review/list.do">[리뷰목록보기]</a>
	<a href="${ctxPath }/review/read.do?no=${modReq.reviewNumber}">
	[리뷰내용보기]</a>
</body>
</html>