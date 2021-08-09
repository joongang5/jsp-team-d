<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내가 쓴 리뷰</title>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
		<div id="myReview">
	내가 쓴 리뷰
	<table >
		<tr>
			<td>번호</td>
			<td>${myReview.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${myReview.id}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${myReview.title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${myReview.content}</td>
		</tr>
		<tr>
			<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}"/>
				<c:if test="${authUser.id == myReview.id}">
					<a href="../review/modify.do?no=${myReview.number}">[리뷰수정]</a>
					<a href="../review/delete.do?no=${myReview.number}">[리뷰삭제]</a>
				</c:if>
			</td>
		</tr>
		</table>
       <div id="menuItem" onclick="menuClick('myPage')">My Page 로 돌아가기</div>
		</div>
</body>
</html>