<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="bbs.message.model.Message" %>
<%@ page import="bbs.message.service.WriteMessageService" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="message" class="bbs.message.model.Message">
	<jsp:setProperty name="message" property="*"/>
</jsp:useBean>
<%
	WriteMessageService writeService = WriteMessageService.getInstance();
	writeService.write(message);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �޽��� ����</title>
</head>
<body>
���Ͽ� �޽��� ������ϴ�.
<br/>
<a href="messagelist.jsp">[��� ����]</a>
</body>
</html>