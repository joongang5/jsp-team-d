<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Ű ����</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
body{
	background-color: #0f0f0f;
	color: #faeef0;	
	
}
#send{
   margin-left: 20%;
   text-align: center;	
}
#emailChangKey{
width: 600px;
height: 45px;
}
#authBtn{
	width: 100px;
	height: 45px;
	
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
<div id = "send">
<h1>���� ����</h1>
<br>

<h3>${newEmail2}�� ����Ű�� ������Ȼ����, Ȯ���Ͻ� �� �Ʒ��� �Է����ּ���.</h3>
<form action ="myPage/userKey.do" method="post">
			 <p><input type="text" id="emailChangKey" placeholder="������ȣ�� �Է��ϼ���" name="emailChangeKey" required> <input type="submit" id="authBtn" name="authBtn" value="Ȯ��" ></p>
		</form>
</div>		
</body>
</html>