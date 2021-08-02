<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
</head>
<body>
	<form action="changePw.do" method="post">
		<p>
			현재 암호:<br />
			<input type="password" name="curPw">
			<c:if test="${errors.curPw }">현재 암호를 입력하세요.</c:if>
			<c:if test="${errors.badCurPw }">현재 암호가 일치하지 않습니다.</c:if>
		</p>
		<p>
			새 암호:<br />
			<input type="password" name="newPw">
			<c:if test="${errors.newPw }">새 암호를 입력하세요.</c:if>
		</p>
		<input type="submit" value="암호 변경">
	</form>
</body>
</html>