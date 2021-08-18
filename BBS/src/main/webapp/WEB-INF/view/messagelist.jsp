<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.message.model.Message"%>
<%@ page import="bbs.message.service.MessageListView"%>
<%@ page import="bbs.message.service.GetMessageListService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String pageNumberStr = request.getParameter("page");
int pageNumber = 1;
if (pageNumberStr != null) {
	pageNumber = Integer.parseInt(pageNumberStr);
}

GetMessageListService messageListService = GetMessageListService.getInstance();
MessageListView viewData = messageListService.getMessageList(pageNumber);

	//System.out.println(viewData.getMessageList().get(0).getId());
	//System.out.println(viewData.getMessageList().get(1).getMessage());


%>
<c:set var="viewData" value="<%=viewData %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 메시지 목록</title>
<style type="text/css">
#nButton{
	text-align: center;
}
#nButton a{
	color: white;
}
</style>
</head>
<body>
	<div style="text-align: center; color: white;">
	<form action="../message/write.do" method="post">
		이름: <input type="text" name="guestName" style="width: 100px;"> <br> 
		암호: <input type="password" name="password" style="width: 100px;"> <br> 
		
		메시지: <textarea name="message" cols="30" rows="3"></textarea>
		<br> <input type="submit" value="메시지 남기기" />
	</form>
	</div>

	
	<c:if test="${viewData.isEmpty()}">
		등록된 메시지가 없습니다.
	</c:if>
	<table border="1">
		<c:forEach var="message" items="${viewData.messageList}">
			<tr>
				<td>메시지 번호: ${message.id} <br /> 손님 이름: ${message.guestName} <br />
					메시지: ${message.message} <br />
					<a href="confirmDeletion.jsp?messageId=${message.id}"></a>
					<!-- 삭제하기 버튼 제거 -->
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id=nButton> 
	<c:forEach var="pageNum" begin="1" end="${viewData.pageTotalCount}">
		<a href="./message.do?page=${pageNum}">[${pageNum}]</a>
	</c:forEach>
	</div>

</body>
</html>