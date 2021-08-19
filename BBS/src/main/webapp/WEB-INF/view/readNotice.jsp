<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/comment.js" type="text/javascript"></script>
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
#cud{
	float: right;
}

#main {
	position: relative;
	float: right;
	height: calc(auto - 35px);
	background-color: #6e6e6e;
}

#mainWrapper {
	position: relative;
	left: 20px;
	height: auto;
	background-color: #2b2b2b;
}
</style>
<script type= "text/javascript">
function del(no){
	//alert("삭제 "+bno+"번 글");
	if(confirm("삭제하시겠습니까?")){
		alert("삭제합니다.")
		location.href="./delete.do?no="+no;
	}
}

function delComment(commentNo, articleNo) {
	if(confirm("삭제하시겠습니까?")){
		alert("삭제합니다.")
		location.href="/BBS/comment/delete.do?commentNo="+commentNo+"&articleNo="+articleNo+"&pageName=notice";
	}
}


$(document).ready(function() {
	var main = document.getElementById('main');
	var mainWrapper = document.getElementById('mainWrapper');
	var menu = document.getElementById('menu');
	
	var maxHeight;
	if (main.offsetHeight > mainWrapper.offsetHeight)
		maxHeight = main.offsetHeight;
	else
		maxHeight = mainWrapper.offsetHeight;
	
	maxHeight += 50;
	maxHeight += 'px';
	
	menu.style.height = maxHeight;
	main.style.height = maxHeight;
	mainWrapper.style.height = maxHeight;
});
</script>
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
			<div id="mainWrapper" >
				<h1 id=head>Notice</h1>
				<table>
					<tr>
						<td style="height: 30px; min-height: 30px; width: 100px;">제목</td>
						<td style="margin-top: 10px; margin-bottom: 10px;">${noticeData.notice.number }번글&nbsp;:&nbsp;<c:out value="${noticeData.notice.title }" /></td>
					</tr>
					<tr>
						<td style="height: 30px; min-width: 70px;  min-height: 30px; margin-bottom: 10px; width: 100px;">작성자</td>
						<td>${noticeData.notice.writer.name }</td>
					</tr>
					
					<tr>
						<td style="height: 30px; min-height: 30px; margin-bottom: 10px; width: 100px;">날짜</td>
						<td><fmt:formatDate value="${noticeData.notice.regDate}" pattern="yyyy-MM-dd hh:mm:ss" />&emsp;&emsp;&emsp;&emsp;&emsp;조회수&emsp;&emsp;${noticeData.notice.readCount}</td>
					</tr>
					
					<tr>
						
						<td colspan="2" style="width: 500px; border-top:3px solid white; text-align: center; min-height: 300px; height: 300px;">${fn:replace(noticeData.content, cn, br)}</td>
						 
					</tr>
					
					<tr>
						<td colspan="2" style="border-top: 3px solid white; min-height: 50px;">
							<c:choose>
							<c:when test="${commentPage.hasContent() }">
								<c:forEach items="${commentPage.content }" var="i">
									<div style="margin: 5px 0;">
										<span>${i.name }(<small>${i.id }</small>) ${i.regDate }</span>
										<span style="float: right;">
											<c:if test="${i.id eq authUser.id }">
												<span class="modifyButton" style="margin-right: 10px; cursor: pointer">수정</span>
												<span style="margin-right: 10px; cursor: pointer" onclick="delComment(${i.no}, ${noticeData.notice.number })">삭제</span>
											</c:if>
										</span>
									</div> 
									<div class="modifyBox">
										<div class="modifyInput">
											<div class="content" style="margin-bottom: 5px;">${i.content }</div>
											<div class="commentNo" style="display: none;">${i.no }</div>
											<div class="articleNo" style="display: none;">${i.articleNo }</div>
											<div class="pageName" style="display: none;">${pageName }</div>
										</div>
									</div>
									<hr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div style="margin-top: 5px;">댓글이 없습니다.</div>
							</c:otherwise>
							</c:choose>
							
							<div id="paging" style="text-align: center; width: 45%; margin-top:10px; margin-right: auto; margin-left: auto;">
								<c:if test="${commentPage.hasContent()}">
									<c:if test="${commentPage.startPage > 5}">
										<a href="/BBS/notice/read.do?no=${noticeData.notice.number }&commentPageNo=${commentPage.startPage -  5}">[이전]</a>
									</c:if>
									<c:forEach var="pNo" begin="${commentPage.startPage}" end="${commentPage.endPage}">
										<a href="/BBS/notice/read.do?no=${noticeData.notice.number }&commentPageNo=${pNo}">[${pNo}]</a>
									</c:forEach>
											<c:if test="${commentPage.endPage < commentPage.totalPages}">
										<a href="/BBS/notice/read.do?no=${noticeData.notice.number }&commentPageNo=${commentPage.startPage +  5}">[다음]</a>
									</c:if>
								</c:if>
							</div>
							
							<c:if test="${authUser ne null }">
								<div class="commentWrite">
								</div>
								<div id="commentInput" style="border: 1px solid white; margin-top: 5px;">
									<div>댓글 쓰기</div>
									<form action="/BBS/comment/write.do" method="post">
										<textarea name="content" style="min-width: 730px"></textarea>
										<input type="hidden" name="articleNo" value="${noticeData.notice.number }">
										<input type="hidden" name="pageName" value="${pageName }">
										<button style="vertical-align:top; width: 50px; height: 30px;">등록</button>
									</form>
								</div>
							</c:if>
						</td>
					</tr>
				</table>
				<div id="nButton">  
					<dl style="text-align: center;"><c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" /> 
						<a href="list.do?pageNo=${pageNo}">[목록]</a>
					</dl> 
					<dl style="text-align: right; margin-bottom: 10px;">
						<c:if test="${authUser.id == 'admin' }">
							<a href="modify.do?no=${noticeData.notice.number }">[수정]</a>&emsp;
							<a onclick="return del(${noticeData.notice.number})" >[삭제]</a>
						</c:if>
					</dl>
				</div>
				<div style="height: 10px"></div>
			</div>
		</div>
	</div>
</body>
</html>