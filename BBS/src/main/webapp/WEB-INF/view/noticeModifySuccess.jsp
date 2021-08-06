<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link href="../css/main.css" rel="stylesheet">
</head>
<body>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
	게시글 수정 완료
	<br>
	${ctxPath = pageContext.request.contextPath }
	<a href="${ctxPath }/notice/list.do">[게시글 목록보기]</a>
	<a href="${ctxPath }/notice/read.do?no=${modReq.noticeNumber }">[게시글 내용보기]</a>
</body>
</html>