<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/main.css" rel="stylesheet">
</head>
<body>
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>

		<form action ="forgot.do" method="post">
			 <input type="text" id="id" name="id" placeholder="가입하신 아이디나 이메일주소를 입력해주세요">
			 <input type="submit" id="emailBtn" name="emailBtn" value="인증 메일 보내기" >	
				<br>
				<c:if test="${! empty param.sns}">
					<span>SNS 가입자는 비밀번호를 변경 할 수 없습니다.</span>
	 			</c:if>
				<c:if test="${! empty param.value}">
					<span>가입된 정보가 없습니다.</span>
	 			</c:if>
				<c:if test="${! empty param.success}">
					<span>이메일로 인증번호가 발송되었습니다.</span>
	 			</c:if>
		</form>	
			 <br>
 		<form action ="setNewPw.do" method="post">
			 <input type="text" id="pwChangKey" placeholder="인증번호를 입력하세요" name="pwChangeKey"> <input type="submit" id="authBtn" name="authBtn" value="확인">
		</form>
		

</body>
</html>