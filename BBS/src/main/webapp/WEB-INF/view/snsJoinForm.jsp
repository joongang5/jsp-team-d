<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link href="./css/main.css" rel="stylesheet">
</head>
<body>
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>

	<form action="join.do" method="post">
		<p>
			<br/> 
				<input type="hidden"	name="id" value="${snsUser.email}">
			<c:if test="${errors.id }">ID를 입력하세요.</c:if>
			<c:if test="${errors.duplicateId }">이미 사용중인 아이디입니다.</c:if>
				<input type="hidden" type="password" name="password" value="${snsUser.access_token }">
			<c:if test="${errors.password }">암호를 입력하세요.</c:if>
				<input name="confirmPassword" type="hidden" value="${snsUser.access_token }">
			<c:if test="${errors.confirmPassword }">암호 확인을 입력하세요</c:if>
			<c:if test="${errors.notMatch }">암호와 확인이 일치하지 않습니다.</c:if>
				<input type="hidden" name="email" value="${snsUser.email}">
			<c:if test="${errors.email }">이메일을 입력하세요</c:if>
		</p>
		<p>
			이름 :<br /> <input type="text" name="name" >
			<c:if test="${errors.name }">이름을 입력하세요.</c:if>
		</p>
		
		<p>
			생일:<br /> <input type="date" name="birth_date">
			<c:if test="${errors.birth_date }">생일을 입력하세요</c:if>
		</p>
		
		<input type="submit" value="가입">
	</form>
</body>
</html>