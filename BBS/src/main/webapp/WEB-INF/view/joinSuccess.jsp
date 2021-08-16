<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 완료</title>
<link href="./css/main.css" rel="stylesheet">
</head>
<body>
<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/view/component/header.jsp" />
		</div>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<h1>성공</h1>
		${param.name }님, 회원 가입에 성공했습니다.<br>
		다시 로그인 해주세요. 
			
			
		</div>
	</div>
</body>
</html>