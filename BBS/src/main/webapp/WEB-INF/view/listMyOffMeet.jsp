<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�� ��ȭ�� �� ��� </title>

</head>
<body>
�� ��ȭ�� �� ���
<table border="1">
		<tr>
			<td>��ȣ</td>
			<td>�ۼ���</td>
			<td>����</td>
			<td>����</td>
		</tr>
		<c:forEach var="offmeet" items="${page.content }">
			<tr>
				<td>${offmeet.number}</td>
				<td>
					<a href="readOffMeet.do?no=${offmeet.number}&pageNo=${OffMeetPage.currentPage}">
							<c:out value="${offmeet.title}" />
					</a>
				</td>
				<td>${offmeet.writerId }</td>
				<td>${offmeet.title }</td>
				<td>${offmeet.content }</td>
			</tr>
		</c:forEach>
	</table>
	<div id="paging">
		<c:set var="command" value="offMeetList.do" scope="request" />
		<c:import url="/WEB-INF/view/component/paging.jsp" />
	</div>

</body>
</html>