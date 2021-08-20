<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<style type="text/css">
textarea{
	width:600px; 
	height:100px; 
	float: left;
	margin-left: 25%;
}
</style>
<script type="text/javascript">
function btnclick(){
	//alert(1);
	document.aaa.submit();
}
</script>
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<div id="mainWrapper" >
	<form name = "aaa" action="modify.do" method="post">
		<input type="hidden" name="no" value="${modReq.offmeetNumber }">
		<p>
			번호<br/>${modReq.offmeetNumber } 
		</p>
		<p>
			
			<input type="text" name="title" required="required" value="${modReq.title }" placeholder="제목을 적어주세요" style="width:600px;height:30px; font-size:10px;float: left;margin-left: 25%;">
			
			<c:if test="${errors.title }">제목을 입력하세요.</c:if>
		</p>
		<p>
			<br/>
			<textarea name="content" required="required" ${modReq.content } placeholder="내용을 적어주세요"></textarea>
			
		</p>
<!-- 		<input type="submit" value="글 수정"> -->
	</form> 
	
	<div id=ddd style="float:right; margin-top: 25%; color: white;">
				<p onclick="btnclick()">[글수정]</p>
				</div> 
	</div>
	</div>
</body>
</html>