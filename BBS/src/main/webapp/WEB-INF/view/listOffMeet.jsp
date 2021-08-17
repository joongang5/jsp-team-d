<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화팟</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">

<style type="text/css">
* {
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
	height: auto;
	min-height: 400px;
	width: auto;
	min-width: 800px;
	border-collapse: collapse;
	color: white;
}
button {

}

#title {
	text-align: left;
	width: 250px;
}

#titleN th{
	border-bottom: 3px solid white;
	max-height: 30px;
}
#paging a{
	color: white;
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
			<table>
					<tr id="titleN" style="text-align: center;	height: 35px;"> 
						<th style="width: 9%;">번호</th>
						<th style="width: 46%">제목</th>
						<th style="width: 11%;">작성자</th>
						<th style="width: 25%;">날짜</th>
						<th style="width: 9%;">조회수</th>
					</tr>
				
				<c:if test="${offmeetPage.hasNoOffMeet()}">
				<tr>
					<td colspan="4"> 게시글이 없습니다.</td>
				</tr>
				</c:if>
				<c:forEach var="offmeet" items="${offmeetPage.content }">
				<tr>
					<td style="text-align: center;">${offmeet.number }</td>
					<td style="text-overflow:ellipsis; white-space:nowrap; max-width:360px; overflow:hidden">
					<a style="color:white; "href="read.do?no=${offmeet.number}&pageNo=${offmeetPage.currentPage }">
					<c:out value="${offmeet.title }"/>
					</a>
					</td>
					<td style="text-align: center;">${offmeet.writer.name }</td>
					<td style="text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${offmeet.modifiedDate }"/></td>
					<td style="text-align: center;">${offmeet.readCount }</td>
				</c:forEach>
			</table>
				
				<div id="paging" style="text-align: center; width: 45%; margin-top:10px; margin-right: auto; margin-left: auto;">
				<c:if test="${offmeetPage.hasOffMeet() }">
					<tr>
						<td colspan="7">
							<c:if test="${offmeetPage.startPage >5 }">
							<a href="list.do?pageNo=${offmeetPage.startPage-5 }">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin= "${offmeetPage.startPage }" end="${offmeetPage.endPage }">
							<a href="list.do?pageNo=${pNo }">[${pNo }]</a>
							</c:forEach>
							<c:if test="${offmeetPage.endPage < offmeetPage.totalPages }">
							<a href="list.do?pageNo=${offmeetPage.startPage +5 }">[다음]</a>
							</c:if>
						</td>
					</tr>		
				</c:if>
			<br>
			<!--로그인시 글쓰기 가능 -->
			<c:if test="${! empty authUser}">	
			<a style="float: right;" href="write.do">
			<button>글쓰기</button>
			</a>
			</c:if>
			</div>
		</div>
	</div>
</div>
	


</body>
</html>