<%@page import="bbs.message.service.InvalidPasswordException"%>
<%@page import="bbs.message.service.DeleteMessageService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	boolean invalidPassword = false;
	
	try{
		DeleteMessageService deleteService = DeleteMessageService.getInstance();
		deleteService.deleteMessage(messageId, password);
	} catch(InvalidPasswordException ex){
		invalidPassword = true;
	}
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �޽��� ������</title>
</head>
<body>
<% if (!invalidPassword){ %>
�޽����� �����Ͽ����ϴ�.
<% } else { %>
�Է��� ��ȣ�� �ùٸ��� �ʽ��ϴ�. ��ȣ�� Ȯ�����ּ���.
<% }%>
<br/>
<a href="list.jsp">[��� ����]</a>

</body>
</html>