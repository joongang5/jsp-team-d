<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			아이디:<br /> <input type="text" name="id" value="${param.id}">
			<c:if test="${errors.id }">ID를 입력하세요.</c:if>
			<c:if test="${errors.duplicateId }">이미 사용중인 아이디입니다.</c:if>
		</p>
		<p>
			이름:<br /> <input type="text" name="name" value="${param.name }">
			<c:if test="${errors.name }">이름을 입력하세요.</c:if>
		</p>
		<p>
			암호:<br/> <input type="password" name="password">
			<c:if test="${errors.password }">암호를 입력하세요.</c:if>
		</p>
		<p>
			암호 확인:<br/><input type="password" name="confirmPassword">
			<c:if test="${errors.confirmPassword }">암호 확인을 입력하세요</c:if>
			<c:if test="${errors.notMatch }">암호와 확인이 일치하지 않습니다.</c:if>
		</p>
		<p>
			이메일:<br/> <input type="email" name="email">
			<c:if test="${errors.email }">이메일을 입력하세요</c:if>
		</p>
		<p>
			생일:<br/> <input type="date" name="birth_date">
			<c:if test="${errors.birth_date }">생일을 입력하세요</c:if>
		</p>
		<p>
		
		</p>
		
		<input type="submit" value="가입">
	</form>
</body>
</html>