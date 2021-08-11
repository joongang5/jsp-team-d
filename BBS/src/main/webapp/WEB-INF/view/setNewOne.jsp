<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="setNewPw.do" method="post">
		<p>
			새 암호:<br />
			<input type="password" name="newPw">
			<c:if test="${errors.newPw }">새 암호를 입력하세요.</c:if>
		</p>
		<input type="submit" value="암호 변경">
	</form>
</body>
</html>