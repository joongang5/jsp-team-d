<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�̸��� �����ϱ� ����</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
body{
	background-color: #0f0f0f;
	color: #faeef0;	
	
}
#success{
    margin-left: 45%;
}
</style>
</head>
<body>
<!-- header�� menu ����Ʈ -->
<div id="header">
         <c:import url="/WEB-INF/view/component/myPageHeader.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>	
<!-- ������� -->

 <div id="success">		
   <h1> ���� ���� </h1>
</div>
</body>
</html>