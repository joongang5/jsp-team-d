<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>${notice.nno }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${notice.id }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${notice.ntitle }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${notice.ncontent }</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<c:if test="${authUser.id ==noticeData.notice.id }">
					<a href="modify.do?no=${noticeData.notice.nno }">[게시글수정]</a>
					<a href="delete.do?no=${noticeData.notice.nno }">[게시글삭제]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>