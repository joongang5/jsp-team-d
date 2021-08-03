<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1>로그인 해주세요</h1>
	<div id="loginBox">
		<form action="login.do" method="post" onsubmit="">
			<span id="inputId"> ID : <input type="text"
				placeholder="아이디를 입력해주세요" name="id" value="${param.id }">
				<c:if test="${errors.id }">ID를 입력하세요.</c:if><br>
			</span>
			<span id="inputPw"> PW : <input type="password"
				name="password" placeholder="비밀번호를 입력해주세요">
				<c:if test="${errors.password }">암호를 입력하세요.</c:if><br>
			</span>
				<c:if test="${errors.idOrPwNotMatch }">
				아이디와 암호가 일치하지 않습니다.<br></c:if>
			<button type="submit">로그인</button><br>
			<a href="./find">ID나 비밀번호를 잃어버리셨나요?</a>
			<a href="join.do">회원가입</a>
		</form>
	</div>
</body>
</html>