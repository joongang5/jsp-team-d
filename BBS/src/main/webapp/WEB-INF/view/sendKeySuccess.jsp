<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Ű ����</title>
<style type="text/css">

body{
	background-color: #0f0f0f;
	color: #faeef0;	
	
}

</style>
</head>
<body>
<!-- header�� menu ����Ʈ -->
<div id="header">
         <c:import url="/WEB-INF/view/component/header.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>	
<!-- ������� -->
<h1>���� ����</h1>
<h2>${newEmail2}�� ����Ű�� ������Ȼ����, Ȯ���Ͻ� �� �Ʒ��� �Է����ּ���.</h2>
<form action ="myPage/userKey.do" method="post">
			 <input type="text" id="emailChangKey" placeholder="������ȣ�� �Է��ϼ���" name="emailChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="Ȯ��">
		</form>
</body>
</html>