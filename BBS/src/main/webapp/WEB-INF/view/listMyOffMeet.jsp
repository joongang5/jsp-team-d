<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 영화팟 글</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
h1 {
	font-size: 50px;
	text-align: center;
	color: white;
}

table {
	margin-left: auto;
	margin-right: auto;
	border-top: 3px solid white;
	border-bottom: 3px solid white;
	width: auto;
	min-width:800px;
	height: auto;
	min-height: 400px;
	boder-collapse: collapse;
	color:white;
}
#paging a{
	color: white;
}

#titleN th{
	border-bottom: 3px solid white;
	max-height: 30px;
}

</style>
</head>
<body>
<div id="container" > 
<div id="header">
         <c:import url="/WEB-INF/view/component/myPageHeader.jsp" />
      </div>
<div id="myPageMenu">
	<c:import url="/WEB-INF/view/component/myPageMenu.jsp" />
</div>	
<div id="main">
			<div id="mainWrapper" style="width: 100%; margin:0;">
			
	<h1>My Offmeet</h1>
	
	<table>
		<tr id="titleN" style="text-align: center;	height: 35px;">
			<th style="width: 9%;">번호</th>
			<th style="width: 11%;">작성자</th>
			<th style="width: 46%">제목</th>
		</tr>
		
		<c:if test="${empty page.content}">
						<tr>
							<td colspan="4" style="text-align:center;">게시글이 없습니다.</td>
						</tr>
		</c:if>
		
		<c:forEach var="offmeet" items="${page.content }">
			<tr id="contentN">
				<td style="text-align: center;">${offmeet.offmeetNo}</td>
			
				<td style="text-align: center;">${offmeet.writerName }</td>
				<td style="text-overflow:ellipsis; white-space:nowrap; max-width:360px; overflow:hidden">
					<a style="color:white;" href="../offmeet/read.do?no=${offmeet.offmeetNo}">
							<c:out value="${offmeet.title}" />
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div id="paging" style="text-align: center; width: 45%; margin-top:10px; margin-right: auto; margin-left: auto;">
		<c:set var="command" value="offMeetList.do" scope="request" />
		<c:import url="/WEB-INF/view/component/paging.jsp" />
	</div>
	
	</div>
	</div>
	</div>
</body>
</html>