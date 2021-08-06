<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<link href="../css/main.css" rel="stylesheet">
<style type="text/css">

</style>
</head>
<body>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
	<table border="1">
		<tr>
			<td colspan="4"><a href="write.do">[공지사항 쓰기]</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
		</tr>
		
		<c:if test="${noticePage.hasNoNotices()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		
		<c:forEach var="notice" items="${noticePage.content}">
			<tr>
				<td>${notice.number }</td>
				<td>
					<a href="read.do?no=${notice.number}&pageNo=${noticePage.currentPage}">
							<c:out value="${notice.title}" />
				</a>
				</td>
				<td>${notice.writer.name}</td>
				<td>${notice.readCount}</td>
			</tr>
		</c:forEach>
		
		<c:if test="${noticePage.hasNotices()}">
			<tr>
				<td colspan="4">
				<c:if test="${noticePage.startPage > 5}">
					<a href="list.do?pageNo=${noticePage.startPage - 5}">[이전]</a>
					</c:if>
					<c:forEach var="pNo"
					begin="${noticePage.startPage}" end="${noticePage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach>
					
					<c:if test="${noticePage.endPage < noticePage.totalPages}">
					<a href="list.do?pageNo=${noticePage.startPage +  5}">[다음]</a>
				</c:if>
		 		</td>
			</tr>
		</c:if>
	</table>
</body>
</html>