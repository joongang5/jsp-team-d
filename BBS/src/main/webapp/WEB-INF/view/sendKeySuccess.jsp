<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>키 인증</title>
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
<!-- header랑 menu 임포트 -->
<div id="header">
         <c:import url="/WEB-INF/view/component/myPageHeader.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>	
<!-- 여기까지 -->
<div id = "send">
<h1>인증 구간</h1>
<br>

<h3>${newEmail2}로 인증키를 보내드렸사오니, 확인하신 후 아래에 입력해주세요.</h3>
<form action ="myPage/userKey.do" method="post">
			 <p><input type="text" id="emailChangKey" placeholder="인증번호를 입력하세요" name="emailChangeKey" required> <input type="submit" id="authBtn" name="authBtn" value="확인" ></p>
		</form>
</div>		
</body>
</html>