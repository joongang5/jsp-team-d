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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
	
	<li><div id="menuItem" onclick="menuClick('myPage')">내 정보</div><hr></li>
	
	<li><div id="menuItem" onclick="menuClick('myPage/reviewList')">내 리뷰</div><hr></li>
	
	<li>내 댓글 <hr></li>
	
	<li><div id="menuItem" onclick="menuClick('myPage/offMeetList')">내 영화팟 글</div></li>
</ul>
</div>
<div id="myPic">
 	 <div id= "profilPreview">
 	 <c:if test="${member.imgName ne null }">						
     <img id="imageProfil" src = "./upload/${member.imgName} " > 
     </c:if>
     <c:if test="${member.imgName eq null }">
     <img id="imageProfil" src ="./img/user2.png">		
     </c:if>
    </div>
    <div id = "cameraIcon">

 	 <label>
 	 	<img id="imageUpload" src="./img/camera.png">
     <!-- input창 안보이게 하고, 아이콘 클릭하면 연결되게 하기-->
     <form action="myPage/profil.do" method ="POST" enctype="multipart/form-data" id="uploadForm">	
     <input id="inputFile" name= "inputFile" style="display:none;" type="file" accept="image/*"  >
    </label>
    <input type="submit" id="profilModi" value="프로필사진 수정하기" >
     </form>
    
    </div>
</div> 
<br>

<div id="myInfo">
 <ul>
		<li>Level : ${member.level} <br><li>
		
		<li>MyPoint : ${member.myPoint} <br></li>
		
		<li>이름 : ${member.name }<br></li>		
		
		<li>이메일 : ${member.email }<br>	
		
			<!-- sns 로그인 사용자들만 암호변경 사용불가 by 강민--> 
	<c:if test="${empty snsAuthUser}">
			<!--  -->
		<li>아이디 : ${member.id}<br> </li>

		<li>비밀번호 : <button onclick="menuClick('changePw')">수정하기</button><br></li>
				 	
	
	
	 
		<form action ="myPage.do" method="post">
			 <input type="email" id="newEmail" name="newEmail" placeholder="새로운 이메일주소를 입력해주세요">
	 
			 <input type="submit" id="emailBtn" name="emailBtn" value="인증 메일 보내기" >	
		</form>	
			 <br>
 		<form action ="myPage/userKey.do" method="post">
			 <input type="text" id="emailChangKey" placeholder="인증번호를 입력하세요" name="emailChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="확인">
		</form>
	
	</c:if>	

	 </li>	
	<li>가입일시 : <fmt:formatDate value="${member.regDate }" pattern="yyyy-MM-dd" /></li>
</ul>	
<div id="menuItem" onclick="menuClick('boxOffice/list')">home</div>
</div>




	
</body>
</html>