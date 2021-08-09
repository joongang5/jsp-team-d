<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


		<form action ="myPage.do" method="post">
			 <input type="email" id="findEmail" name="findEmail" placeholder="가입하신 이메일주소를 입력해주세요">
	 
			 <input type="submit" id="emailBtn" name="emailBtn" value="인증 메일 보내기" >	
		</form>	
			 <br>
 		<form action ="myPage/userKey.do" method="post">
			 <input type="text" id="emailChangKey" placeholder="인증번호를 입력하세요" name="emailChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="확인">
		</form>
		
			<li>비밀번호 : <button onclick="menuClick('changePw')">수정하기</button><br></li>

</body>
</html>