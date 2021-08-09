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
<title>방명록 메시지 남김</title>
</head>
<body>
방명록에 메시지 남겼습니다.
<br/>
<a href="messagelist.jsp">[목록 보기]</a>
</body>
</html>