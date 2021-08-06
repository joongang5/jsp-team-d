<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
		<div id="myReview">
	����Խ����Դϴ�.
	<table >
		<tr>
			<td>��ȣ</td>
			<td>${myReview.number}</td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td>${myReview.id}</td>
		</tr>
		<tr>
			<td>����</td>
			<td>${myReview.title}</td>
		</tr>
		<tr>
			<td>����</td>
			<td>${myReview.content}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}"/>
			<a href="myPage/readReview.do?pageNo=${pageNo}">[���]</a>
				<c:if test="${authUser.id == myReview.id}">
					<a href="../review/modify.do?no=${myReview.number}">[�������]</a>
					<a href="../review/delete.do?no=${myReview.number}">[�������]</a>
				</c:if>
			</td>
		</tr>
		</table>
		</div>
</body>
</html>