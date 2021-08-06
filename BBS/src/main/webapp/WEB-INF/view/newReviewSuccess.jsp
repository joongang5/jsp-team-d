<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 등록</title>
</head>
<body>
	리뷰를 등록했습니다.
	<br>
	<!-- ${ctxPath = pageContext.request.contextPath }  -->
	<a href="${ctxPath }/review/list.do">[리뷰목록보기]</a>
	<a href="${ctxPath }/review/read.do?no=${newReviewNo}">[리뷰내용보기]</a>
</body>
</html>