<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link href="../css/main.css" rel="stylesheet">
</head>
<body>
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>
	게시글을 등록했습니다.

	<br> ${ctxPath = pageContext.request.contextPath }
	<a href="${ctxPath }/notice/list.do">[게시글목록보기]</a>
	<a href="${ctxPath }/notice/read.do?no=${newNoticeNo }">[게시글내용보기]</a>
</body>
</html>