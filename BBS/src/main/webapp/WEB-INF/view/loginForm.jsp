<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Login</title>
</head>
<body>
	<h1>�α��� ���ּ���</h1>
	<div id="loginBox">
		<form action="login.do" method="post" onsubmit="">
			<span id="inputId"> ID : <input type="text"
				placeholder="���̵� �Է����ּ���" name="id" value="${param.id }">
				<c:if test="${errors.id }">ID�� �Է��ϼ���.</c:if><br>
			</span>
			<span id="inputPw"> PW : <input type="password"
				name="password" placeholder="��й�ȣ�� �Է����ּ���">
				<c:if test="${errors.password }">��ȣ�� �Է��ϼ���.</c:if><br>
			</span>
				<c:if test="${errors.idOrPwNotMatch }">
				���̵�� ��ȣ�� ��ġ���� �ʽ��ϴ�.<br></c:if>
			<button type="submit">�α���</button><br>
			<a href="./find">ID�� ��й�ȣ�� �Ҿ�����̳���?</a>
			<a href="join.do">ȸ������</a>
		</form>
	</div>
</body>
</html>