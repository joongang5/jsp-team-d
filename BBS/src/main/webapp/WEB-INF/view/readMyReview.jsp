<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �� ����</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
	<div id="myReview">
		���� �� ����
		<table border="1" >
		
		<tr>
			<td>��ȣ</td>
			<td>${myReview[0].number}</td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td>${myReview[0].name}</td>
		</tr>
		<tr>
			<td>����</td>
			<td><c:out value="${myReview[0].title}"/></td>
		</tr>
		<tr>
			<td>����</td>
			<td><c:out value="${myReview[0].content}"/></td>
		</tr>

	</table>
	</div>
</body>
</html>