<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	���� ����Ʈ
	<table border="1">
		<tr>
			<td>��ȣ</td>
			<td>�ۼ���</td>
			<td>����</td>
		</tr>
		<c:forEach var="review" items="${page.content }">
			<tr>
				<td>${review.number}</td>
			
				<td>${review.id }</td>
				<td>
					<a href="../review/read.do?no=${review.number}&pageNo=${pageNum}">
							<c:out value="${review.title}" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id="paging">
		<c:set var="command" value="reviewList.do" scope="request" />
		<c:import url="/WEB-INF/view/component/paging.jsp" />
	</div>
</body>
</html>