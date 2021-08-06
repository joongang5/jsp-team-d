<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="./css/myPage.css" rel="stylesheet">
<script type="text/javascript" src="/BBS/js/menu.js"></script>
<script type="text/javascript" src="/BBS/js/myPage.js"></script>
</head>
<body>
<div id="title">
My <br>
Favorits
</div>
<div id="myPageList">
<ul>
	<li>My Page <hr></li>
	
	<li>내 정보<hr></li>
	
	<li><div id="menuItem" onclick="menuClick('myPage/readReview')">내 리뷰</div><hr></li>
	
	<li>내 댓글 <hr></li>
	
	<li>영화 친구</li>
</ul>
</div>
<div id="myPic">
 	 <label>
     <img id="imageUpload" src="./img/user2.png">
     <!-- input창 안보이게 하고, 아이콘 클릭하면 연결되게 하기-->
     <input id="inputFile" style="display:none;" type="file" accept="image/*"  >
</label>
</div> 
<br>

<div id="myInfo">
 <ul>
	<li>아이디 : ${member.id}<br> </li>

	<li>이름 : ${member.name }<br></li>		
	<li>비밀번호 : <button onclick="menuClick('changePw')">수정하기</button><br></li>
				 	
		
	<li>이메일 : ${member.email }
	
	 <br>	
<form action ="myPage.do" method="post">
	 <input type="email" id="newEmail" name="newEmail" placeholder="새로운 이메일주소를 입력해주세요">
	 
	 <input type="submit" id="emailBtn" name="emailBtn" value="인증 메일 보내기" >	
</form>	
	 <br>
 <form action ="myPage/userKey.do" method="post">
	 <input type="text" id="emailChangKey" placeholder="인증번호를 입력하세요" name="emailChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="확인">
</form>
	
	 </li>	
	<li>가입일시 : <fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd" /></li>
</ul>	
<div id="menuItem" onclick="menuClick('index')">home</div>
</div>




	
</body>
</html>