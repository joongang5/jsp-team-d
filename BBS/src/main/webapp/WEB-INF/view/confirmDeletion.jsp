<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� �޽��� ���� Ȯ��</title>
</head>
<body>

<form action="deleteMessage.jsp" method="post">
<input type="hidden" name="messageId" value="${param.messageId }">
�޽����� �����Ϸ��� ��ȣ�� �Է��ϼ���:<br>
��ȣ: <input type="password" name="password"> <br>
<input type="submit" value="�޽��� �����ϱ�">

</form>
</body>
</html>