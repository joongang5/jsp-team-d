<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>방명록 메시지 삭제 확인</title>
</head>
<body>

<form action="deleteMessage.jsp" method="post">
<input type="hidden" name="messageId" value="${param.messageId }">
메시지를 삭제하려면 암호를 입력하세요:<br>
암호: <input type="password" name="password"> <br>
<input type="submit" value="메시지 삭제하기">

</form>
</body>
</html>