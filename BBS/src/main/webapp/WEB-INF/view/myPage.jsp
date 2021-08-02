<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap');
body{
	background-color: #0f0f0f;
	color: #faeef0;	
	font-family: 'Nanum Myeongjo', serif;
}
img{
 margin: 0 auto;
 margin-left :19%;
 width: 200px;
 height: 200px;
}
#myPageList{
	float:left;
}
#myPageList > ul {
	list-style:none;
}
#myInfo > ul {
	list-style:none;
}

#myInfo{
	margin: 0 auto;
	margin-left :35%;
	width: 70%;
	height: 40%;
}
#myPic{
	margin:0 auto;
	box-sizing:border-box;
	padding:5px;
	width: 30%;
	height: 55%;
	border: 1px solid #faeef0 ;
}
#title{
	float: right;
	margin-top: 70px;
	padding-left: 20px;
	padding-bottom: 70px;
	font-size: 72pt;
	-webkit-transform: rotate(90deg);	
	-ms-transform:roate(90deg);
	transform:rotate(90deg);
	box-sizing:border-box;
	
}
</style>
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
	<li>내 리뷰</li>
	<hr>
	<li>내 댓글</li>
	<hr>
	<li>영화 친구</li>
</ul>
</div>
<div id="myPic">
 <img alt="내사진" src="./img/finn.jpg">

</div> 
<br>
 <div id="myInfo">
 <form action="" method="post" id="myInfoModify">
 <ul>
	<li>아이디 : ${member.id} </li>
	<br>
	<li>이름 : ${member.name }</li>
	<br>
	<li>비밀번호 : <input type="password" placeholder="비밀번호 수정하기" required></input> <button type="submit" form="myInfoModify">수정</button> </button></li>
	<br>
	<li>이메일 : ${member.email }</li>
	<br>
	<li>가입일시 : <fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd" /></li>
</ul>		
</form>
</div>




	
</body>
</html>