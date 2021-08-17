<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
<meta charset="EUC-KR">
<title>���� �޽��� ���</title>
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
		�̸�: <input type="text" name="guestName" style="width: 100px;"> <br> 
		��ȣ: <input type="password" name="password" style="width: 100px;"> <br> 
		
		�޽���: <textarea name="message" cols="30" rows="3"></textarea>
		<br> <input type="submit" value="�޽��� �����" />
	</form>
	</div>

	
	<c:if test="${viewData.isEmpty()}">
		��ϵ� �޽����� �����ϴ�.
	</c:if>
	<table border="1">
		<c:forEach var="message" items="${viewData.messageList}">
			<tr>
				<td>�޽��� ��ȣ: ${message.id} <br /> �մ� �̸�: ${message.guestName} <br />
					�޽���: ${message.message} <br />
					<a href="confirmDeletion.jsp?messageId=${message.id}">[�����ϱ�]</a>
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