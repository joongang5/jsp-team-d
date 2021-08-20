<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" ></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
<link href="../css/main.css" rel="stylesheet">
<link href="../css/menu.css" rel="stylesheet">
<link rel="stylesheet" href="../css/newOffMeetForm.css" />
<style type="text/css">
#write{

	

}
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
				<div id="write">
					<form name="aaa" action="write.do" method="post">
						<div id="mapResult1"></div>
						<input type="text" name="title" required="required" placeholder="제목을 적어주세요" style="width:600px;height:30px; font-size:10px;float: left;margin-left: 25%;">
						<div id=test>&nbsp
						</div>
<%-- 						<c:if test="${errors.title }">제목을 입력하세요.</c:if>
 --%>						<br>
						<textarea name="content" required="required" placeholder="내용을 적어주세요"></textarea>
						<br><br><br><br><br>
						
						<!-- <button type="submit" style="margin-left: 25%;">글쓰기</button> -->
					</form>
					<br>
					
				</div>
				<div class="map_wrap">
					<div id="map"
						style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
					
					<div id="menu_wrap" class="bg_white">
						<div class="option">
							<div>
								<form onsubmit="searchPlaces(); return false;">
									키워드 : <input type="text" value="강남" id="keyword" size="15">
									<button type="submit">검색하기</button>
								</form>
							</div>
						</div>
						<hr>
						<ul id="placesList"></ul>
						<div id="pagination" ></div>
					</div>
				</div>
				<div id=ddd style="float:right;">
				<p onclick="location.href='/BBS/offmeet/list.do'">[게시판으로]</p>
				</div>
				<div id=ddd style="float:right;">
				<p onclick="btnclick()">[글쓰기]&nbsp&nbsp</p>
				</div> 
				
				<div id="mapResult2" style="margin-left: 25%"></div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=98b4053002d0f20ff304b2d8ef58bfe1&libraries=services"></script>
				<script type="text/javascript" src="../js/newOffMeetForm.js"></script>
			</div>				
		</div>
	</div>
	
</body>
</html>