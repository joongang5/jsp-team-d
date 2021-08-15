<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>키 인증</title>
<style type="text/css">

body{
	background-color: #0f0f0f;
	color: #faeef0;	
	
}

</style>
</head>
<body>
<!-- header랑 menu 임포트 -->
<div id="header">
         <c:import url="/WEB-INF/view/component/header.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>	
<!-- 여기까지 -->
<h1>인증 구간</h1>
<h2>${newEmail2}로 인증키를 보내드렸사오니, 확인하신 후 아래에 입력해주세요.</h2>
<form action ="myPage/userKey.do" method="post">
			 <input type="text" id="emailChangKey" placeholder="인증번호를 입력하세요" name="emailChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="확인">
		</form>
</body>
</html>