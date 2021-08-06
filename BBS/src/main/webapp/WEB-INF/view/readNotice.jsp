<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
<link href="../css/main.css" rel="stylesheet">
<style type="text/css">
</style>
<script type= "text/javascript">
function del(no){
	//alert("삭제 "+bno+"번 글");
	if(confirm("삭제하시겠습니까?")){
		alert("삭제합니다.")
		location.href="./delete.do?no="+no;
	}
}
</script>
</head>
<body>
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>
	<div id="main">
		<table border="1" width="100%">
			<tr>
				<td>제목</td>
				<td>${noticeData.notice.number }번글: <c:out value="${noticeData.notice.title }" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${noticeData.notice.writer.name }</td>
			</tr>
			
			<tr>
				<td>날짜</td>
				<td><fmt:formatDate value="${noticeData.notice.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>${fn:replace(noticeData.content, cn, br)}</td>
				
			</tr>
			
			
			<tr>
				<td colspan="2">
					<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" /> 
						<a href="list.do?pageNo=${pageNo}">[목록]</a> 
							<c:if test="${authUser.id == noticeData.notice.writer.id }">
						<a href="modify.do?no=${noticeData.notice.number }">[게시글수정]</a>
						
						<a onclick="return del(${noticeData.notice.number})" >[게시글삭제]</a>
							</c:if></td>
			</tr>
		</table>
	</div>
</body>
</html>