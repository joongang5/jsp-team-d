<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>삭제하시겠습니까?</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
삭제하시겠습니까?
<form action="delete.do" method="post">
<input  type="hidden" name="no" value="${modReq.reviewNumber}">
<input type="submit" value="삭제">
<div id="menuItem" onclick="menuClick('review/list')">돌아가기</div>
</form>
</body>
</html>