<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
</head>
<body>
	<table border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>${offmeetData.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${offmeetData.writer.name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value="${offmeetData.title }"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><c:out value="${offmeetData.content }"/></td>
		</tr>
		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<a href="list.do?pageNo=${pageNo}">[목록]</a>
				<c:if test="${authUser.id == offmeetData.writer.id }">
					<a href="modify.do?no=${offmeetData.number }">[게시글수정]</a>
					<a href="delete.do?no=${offmeetData.number }">[게시글삭제]</a>
				</c:if>
			</td>
		</tr>
	</table>
	<div style="margin-top: 25px; text-align: center;">
		<textarea style="width: 70%; height: 70px;" name="contents" class="form-control"></textarea>&nbsp;
		<button type="button" class="btn btn-square-toround btn-bordered-info" style="width: 80px; height: 70px;">댓글</button>
	</div>
</body>
</html>