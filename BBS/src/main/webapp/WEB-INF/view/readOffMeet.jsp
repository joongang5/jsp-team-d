<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet">
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
h1{
	font-size:50px;
	text-align:center;
	color: white;
}
table{
	margin-left: auto; 
	margin-right: auto;
	border-top: 3px solid white; 
	border-bottom: 3px solid white; 
	height: auto;
	min-height: 400px;
	width:auto;
	min-width:800px;
	max-width:800px;
	color: white;
	border-collapse: collapse;
}
#nButton{
	margin-top: 10px;
	width: 45%; 
	margin-top:10px; 
	margin-right: auto; 
	margin-left: auto;
}
#nButton a{
	color: white;
}
#main {
	position: relative;
	float: right;
	width: calc(100% - 150px);
	height: calc(auto - 35px);
	background-color: #6e6e6e;
}

#mainWrapper {
	position: relative;
	left: 20px;
	width: calc(100% - 20px);
	height: auto;
	background-color: #2b2b2b;
}
</style>	
</head>
<body>
<div id="container">
    <div id="header">
         <c:import url="/WEB-INF/view/component/header.jsp" />
    </div>
	<div id="menu">
		<c:import url="/WEB-INF/view/component/menu.jsp" />
	</div>
	<div id="main">
	<div id="mainWrapper">
	<h1>Movie Gathering</h1>
	<table width="100%">
		<tr>
			<td style="height: 30px; min-height: 30px; width: 100px;">제목</td>
			<td style="margin-top: 10px; margin-bottom: 10px;"><c:out value="${offmeetData.title }"/></td>
		</tr>
		<tr>
			<td style="height: 30px; min-width: 70px;  min-height: 30px; margin-bottom: 10px; width: 100px;">작성자</td>
			<td>${offmeetData.writer.name }</td>
		</tr>
		<tr>
			<td colspan="2" style="width: 500px; border-top:3px solid white; text-align: center; min-height: 300px; height: 300px;"><c:out value="${offmeetData.content }"/></td>
		</tr>

		<tr>
			<td style="height: 30px; min-height: 30px; margin-bottom: 10px;	border-top: 3px solid white;width: 100px; ">상호명</td>
			<td style="	border-top: 3px solid white; "><c:out value="${offmeetData.sangho }"/></td>
		</tr>
		<tr>
			<td style="height: 30px; min-height: 30px; margin-bottom: 10px;width: 100px;">주소</td>
			<td><c:out value="${offmeetData.juso }"/></td>
		</tr>
		<tr>
			<td style="height: 30px; min-height: 30px; margin-bottom: 10px;width: 100px;">전화번호</td>
			<td><c:out value="${offmeetData.tel }"/></td>
		</tr>
	</table>
		
		
			<div id="nButton">
			<dl style="text-align: center;">  
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"/>
				<a href="list.do?pageNo=${pageNo}">[목록]</a></dl>
				
				<dl style="text-align: right; margin-bottom: 10px;">
				<c:if test="${authUser.id == offmeetData.writer.id }">
					<a href="modify.do?no=${offmeetData.number }">[게시글수정]</a>
					<a href="delete.do?no=${offmeetData.number }">[게시글삭제]</a>
				</c:if></dl>
			</div>
		
	
	

	</div>
	</div>
	</div>

</body>
</html>