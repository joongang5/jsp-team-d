<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<style type="text/css">
#myPageList{
	float:left;
}
#myPageList > ul {
	list-style:none;
}
#myInfo > ul {
	list-style:none;
}
body{
	background-color: #0f0f0f;
	color: #faeef0;	
}
#myInfo{
	margin: 0 auto;
	margin-left :20%;
	width: 70%;
	height: 40%;
}
#myPic{
	margin:0 auto;
	width: 35%;
	height: 50%;
	border: 1px solid #faeef0 ;
}
img{
 margin: 0 auto;
 margin-left :20%;
 width: 200px;
 height: 200px;
}
</style>
<body>
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
 <ul>
	<li>${member.id} </li>
	<li>${member.name }</li>
	<li>${member.password }</li>
	<li>${member.email }</li>
	<li>${member.regDate }</li>
</ul>		
</div>



</div>
	
</body>
</html>