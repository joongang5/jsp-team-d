<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>영화평가 라운지</title>
<!-- 부트스트랩 css 추가하기 -->
<link rel="stylesheet" href="../css/bootstrap.min.css">
<!-- 커스텀 css 추가하기 -->
<link rel="stylesheet" href="../css/custom.min.css">
</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.jsp">영화평가 라운지</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">메인</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown"
					data-toggle="dropdown"> 회원 관리 </a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<a class="dropdown-item" href="../login.do">로그인</a> <a
							class="dropdown-item" href="../join.do">회원가입</a> <a
							class="dropdown-item" href="../logout.do">로그아웃</a>
					</div></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="내용을 입력하세요." aria-label="Search">
				<button class="btn btn-outline-info my-2 my-sm-0" type="submit">검색</button>
			</form>
		</div>
	</nav>
	<section class="container">
		<form method="get" action="./rating.jsp" class="form-inline mt-3">
			<select name="movieDivide" class="form-control mx-1 mt-2">
				<option value="전체">전체</option>
				<option value="현재 상영작">현재 상영작</option>
				<option value="지난 개봉작">지난 개봉작</option>
				<option value="기타">기타</option>
			</select> <input type="text" name="search" class="form-control mx-1 mt-2"
				placeholder="내용을 입력하세요">
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
			<a class="btn btn-success mx-1 mt-2" data-toggle="modal"
				href="#registerModal">등록하기</a> <a class="btn btn-danger mx-1 mt-2"
				data-toggle="modal" href="#reportModal">신고</a>
		</form>
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						뷰티플마인드&nbsp;<small>이브이</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
		aria-labelledby="modal" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">내 영화평가 등록</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="./ratingRegisterAction.jsp" method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label>영화명</label> <input type="text" name="movieName"
									class="form-control" maxlength="20">
							</div>
							<div class="form-group col-sm-6">
								<label>영화감독</label> <input type="text" name="movieName"
									class="form-control" maxlength="20">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-4">
								<label>관람 연도</label> <select name="movieYear"
									class="form-control">
									<option value="2011">2011</option>
									<option value="2012">2012</option>
									<option value="2013">2013</option>
									<option value="2014">2014</option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
									<option value="2021" selected>2021</option>
									<option value="2022">2022</option>
									<option value="2023">2023</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>관람 나이</label> <select name="agegroupDivide"
									class="form-control">
									<option value="10대">10대</option>
									<option value="20대" selected>20대</option>
									<option value="30대">30대</option>
									<option value="40대">40대</option>
									<option value="50대">50대</option>
									<option value="60대 이상">60대</option>
								</select>
							</div>
							<div class="form-group col-sm-4">
								<label>영화장르</label> <select name="genreDivide"
									class="form-control">
									<option value="액션" selected>액션</option>
									<option value="SF/판타지">SF/판타지</option>
									<option value="로멘스">로멘스</option>
									<option value="코믹">코믹</option>
									<option value="공포/스릴러">공포/스릴러</option>
									<option value="기타">기타</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label>제목</label> <input type="text" name="ratingTitle"
								class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea name="ratingContent" class="form-control"
								maxlength="2048" style="height: 180px;"></textarea>
						</div>
						<div class="form-row">
							<div class="form-group col-sm-3">
								<label>종합</label> <select name="totalScore" class="form-control">
									<option value="5star">★★★★★</option>
									<option value="4star">☆★★★★</option>
									<option value="3star" selected>☆☆★★★</option>
									<option value="2star">☆☆☆★★</option>
									<option value="1star">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>몰입도</label> <select name="immersionScore"
									class="form-control">
									<option value="5star">★★★★★</option>
									<option value="4star">☆★★★★</option>
									<option value="3star" selected>☆☆★★★</option>
									<option value="2star">☆☆☆★★</option>
									<option value="1star">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>영상미</label> <select name="visualbeautyScore"
									class="form-control">
									<option value="5star">★★★★★</option>
									<option value="4star">☆★★★★</option>
									<option value="3star" selected>☆☆★★★</option>
									<option value="2star">☆☆☆★★</option>
									<option value="1star">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>메시지</label> <select name="messageScore"
									class="form-control">
									<option value="5star">★★★★★</option>
									<option value="4star">☆★★★★</option>
									<option value="3star" selected>☆☆★★★</option>
									<option value="2star">☆☆☆★★</option>
									<option value="1star">☆☆☆☆★</option>
								</select>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-success">등록하기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
		aria-labelledby="modal" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="./reportAction.jsp" method="post">
						<div class="form-group">
							<label>신고제목</label> <input type="text" name="reportTitle"
								class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>신고내용</label>
							<textarea name="reportContent" class="form-control"
								maxlength="2048" style="height: 180px;"></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-danger">신고하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 제이쿼리 자바스크립트 추가하기 -->
	<script src="../js/jquery.min.js"></script>
	<!-- Popper 자바스크립트 추가하기 -->
	<script src="../js/popper.js"></script>
	<!-- 부트스트랩 자바스크립트 추가하기 -->
	<script src="../js/bootstrap.min.js"></script>

</body>



</html>