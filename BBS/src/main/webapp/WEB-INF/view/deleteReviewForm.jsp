<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�����Ͻðڽ��ϱ�?</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
�����Ͻðڽ��ϱ�?
<form action="delete.do" method="post">
<input  type="hidden" name="no" value="${modReq.reviewNumber}">
<input type="submit" value="����">
<div id="menuItem" onclick="menuClick('review/list')">���ư���</div>
</form>
</body>
</html>