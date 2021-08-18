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
<title>영화 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
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
	min-height: 300px;
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
p {
	line-height: 1.5em; 
}
.starRatings {
  color: #aaa9a9; 
  position: relative;
  unicode-bidi: bidi-override;
  width: max-content;
  -webkit-text-fill-color: transparent; 
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
}
 
.starRatingsFill {	
  color: red;
  padding: 0;
  position: absolute;
  z-index: 1;
  display: flex;
  top: 0;
  left: 0;
  overflow: hidden;
  -webkit-text-fill-color: gold;
}
 
.starRatingsBase {
  z-index: 0;
  padding: 0;
}
#parent{
	display:flex;
}
</style>
<script type= "text/javascript">
function del(no){
	if(confirm("삭제하시겠습니까?")){
		alert("삭제합니다.")
		location.href="./delete.do?no="+no;
	}
}
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
			<div id="mainWrapper">
				<br /><br />
				<table>
					<tr>
						<td style="width: 150px;"><img SRC="${movieView.image }"></td>
						<td style="vertical-align: top;">
							<div style="font-weight: bold; font-size: 30px; margin-top: 25px;">
								${movieView.movieNm }
							</div>
							<div style="margin-left: 3px;">
								<c:if test="${not empty movieView.movieNmEn}">
									${movieView.movieNmEn },									
								</c:if>
								<c:if test="${empty movieView.movieNmEn}">
									-,									
								</c:if>
								${movieView.prdtYear }
							</div>
							<div id="parent" style="margin-left: 3px; margin-top: 30px">
								<label>네티즌 평점</label>
								<div class="starRatings">
									<div class="starRatingsFill" style= 'width: ${movieView.userRating * 10}%;'> <!-- 나란히 두려면 10 곱하긴 해야겠네요 -->
										<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
									</div>
									<div class="starRatingsBase">
										<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
									</div>
								</div>
								<label>${movieView.userRating }</label>
							</div>
								
						</td>
					</tr>
					<tr style="border-top: 3px solid white;">
						<td colspan="2" style="width: 500px; vertical-align: top; min-height: 200px; height: 200px;">
							<br />
							<p>개봉일 : ${movieView.openDt }</p>
							<p>영화유형 : ${movieView.typeNm }</p>
							<p>제작상태 : ${movieView.prdtStatNm }</p>
							<p>제작국가 : ${movieView.nationAlt }</p>
							<p>영화장르 : ${movieView.genreAlt }</p>
							<p>영화감독 : ${movieView.directors }</p>
						</td>
					</tr>
					
				</table>
				<div id="nButton">  
					<dl style="text-align: center;">
						<dt>
							<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
							<a href="list.do?pageNo=${pageNo }">[목록]</a>
						</dt>
					</dl> 
						
					<dl style="text-align: right; margin-bottom: 10px;">
						<c:if test="${authUser.id == 'admin' }">
							<a href="modify.do?no=${movieView.movieCd }">[수정]</a>&emsp;
							<a onclick="return del(${movieView.movieCd })" >[삭제]</a>
						</c:if>
					</dl>
				</div>
			</div>
		</div>
	</div>
</body>
</html>