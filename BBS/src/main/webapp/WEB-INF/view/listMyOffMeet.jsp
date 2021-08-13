<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내 영화팟 글 목록 </title>

</head>
<body>
내 영화팟 글 목록
<table border="1">
		<tr>
			<td>번호</td>
			<td>작성자</td>
			<td>제목</td>
		</tr>
		<c:forEach var="offmeet" items="${page.content }">
			<tr>
				<td>${offmeet.offmeetNo}</td>
				<td>${offmeet.writerName }</td>
				<td>
					<a href="../offmeet/read.do?no=${offmeet.offmeetNo}">
							<c:out value="${offmeet.title}" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id="paging">
		<c:set var="command" value="offMeetList.do" scope="request" />
		<c:import url="/WEB-INF/view/component/paging.jsp" />
	</div>

</body>
</html>