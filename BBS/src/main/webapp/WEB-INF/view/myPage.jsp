<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="./css/myPage.css" rel="stylesheet">
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>
<body>
<div id="title">
My <br>
Favorits
</div>
<div id="myPageList">
<ul>
	<li>My Page</li>
	<hr>
	<li>내 정보</li>
	<hr>
	<li><div id="menuItem" onclick="menuClick('myPage/readReview')">내 리뷰</div></li>
	<hr>
	<li>내 댓글</li>
	<hr>
	<li>영화 친구</li>
</ul>
</div>
<div id="myPic">
 	 프로필사진
</div> 
<br>
 <div id="myInfo">

 <ul>
	<li>아이디 : ${member.id} </li>
	<br>
	<li>이름 : ${member.name }</li>
	<br>		
	<li>비밀번호 : <button onclick="menuClick('changePw')">수정하기</button></li>
				 	
	<br>	
	<li>이메일 : ${member.email } <input type="email" placeholder="바꿀 이메일을 입력하세요" /><button type="submit" form="myInfo">수정하기</button> </li>
	<br>
	<li>가입일시 : <fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd" /></li>
</ul>	

</div>




	
</body>
</html>