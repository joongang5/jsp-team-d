<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 업로드</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
body{
	background-color: #0f0f0f;
	color: #faeef0;	
	
}
#myPageMenu{
  width:130px;
}
#profile{
  margin-left: 45%;
}
</style>
<script type="text/javascript" src="/BBS/js/menu.js"></script>
</head>

<body>
<div id="header">
         <c:import url="/WEB-INF/view/component/myPageHeader.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>
<div id="profile">	      
<h2>프로필 사진 갱신 완료</h2>
</div>	

</body>
</html>