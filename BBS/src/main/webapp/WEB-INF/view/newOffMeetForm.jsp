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
</head>
<body>
	<div id="container">
		<div id="menu">
			<c:import url="/WEB-INF/view/component/menu.jsp" />
		</div>
		<div id="main">
			<div id="mainWrapper">
				<div id="write">
					<form action="write.do" method="post">
						<div id="mapResult1"></div>
						<input type="text" name="title" required="required" placeholder="제목을 적어주세요">
						<c:if test="${errors.title }">제목을 입력하세요.</c:if>
						<textarea name="content" required="required"></textarea>
						<input type="file" name="file1">
						<button type="submit">글쓰기</button>
					</form>
					<br>					
					<p onclick="location.href='/BBS/offmeet/list.do'">게시판으로</p>
				</div>
				<div class="map_wrap">				
					<div id="map"
						style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
					
					<div id="menu_wrap" class="bg_white">
						<div class="option">
							<div>
								<form onsubmit="searchPlaces(); return false;">
									키워드 : <input type="text" value="강남 CGV" id="keyword" size="15">
									<button type="submit">검색하기</button>
								</form>
							</div>
						</div>
						<hr>
						<ul id="placesList"></ul>
						<div id="pagination"></div>
					</div>
				</div>
				<div id="mapResult2"></div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f7c4b2fc5ab90c2479e74ec47a34e03b&libraries=services"></script>
				<script type="text/javascript" src="../js/newOffMeetForm.js"></script>
			</div>			
		</div>
	</div>
</body>
</html>