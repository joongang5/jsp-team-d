<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<style type="text/css">

.navbar{

 background-color:gray !important;
 border-radius: 10px;
 
}

</style>
</head>
<body>
<div class="p-3 mb-2 bg-dark text-black">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="rating.do" onclick="return confirm('평가리스트 최신화 완료')">D'movie 영화평가 라운지</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="../boxOffice/list.do">|&nbsp;&nbsp;&nbsp;&nbsp;MAIN PAGE</a></li>
					<!-- 
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown"
					data-toggle="dropdown"> JOIN US (추가 계정필요)</a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<a class="dropdown-item" href="login.do">로그인</a> <a
							class="dropdown-item" href="join.do">회원가입</a> <a
							class="dropdown-item" href="logout.do">로그아웃</a>
					</div></li>
			 <form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="내용을 입력하세요." aria-label="Search">
				<button class="btn btn-outline-dark my-2 my-sm-0" type="submit">검색</button>
			</form>
					 -->
			
			</ul>
		</div>
	</nav>

	<section class="container">
		<form method="get" action="./rating.jsp" class="form-inline mt-3">
			<!--  <select name="movieDivide" class="form-control mx-1 mt-2">
				<option value="전체">전체</option>
				<option value="현재 상영작">현재 상영작</option>
				<option value="지난 개봉작">지난 개봉작</option>
				<option value="기타">기타</option>
			</select> <input type="text" name="search" class="form-control mx-1 mt-2"
				placeholder="내용을 입력하세요">
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>-->
			<a class="btn btn-success mx-auto mt-2 w-100" data-toggle="modal"
				href="#registerModal" onclick="return confirm('로그인 하셨습니까?')">등록하기</a> 
				<!-- <a class="btn btn-danger mx-1 mt-2"	data-toggle="modal" href="#reportModal">신고</a> -->
		</form>

		
		<!-- 링크 넣는 곳 -->
		
<%-- 		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						${rating.movieName}&nbsp;<small>${rating.directorName}</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">${rating.getTotalScore}</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					${rating.ratingTitle}<small>${rating.userID}</small>
				</h5>
				<p class="card-text">${rating.ratingContent}</p>
				<div class="row">
					<div class="col-9 text-left">
						몰입도<span style="color: red;">${rating.getImmersionScore}</span> 영상미<span
							style="color: red;">${rating.visualbeautyScore}</span> 메시지<span style="color: red;">${rating.messageScore}</span>
						<span style="color: green;">(추천: ${rating.likeCount})</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?ratingID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?ratingID=">삭제</a>
					</div>
				</div>
			</div>
		</div> --%>

		<!-- 하단 예시 -->
		<c:forEach var="rating" items="${page.content}">
			<div class="card bg-light mt-3">
				<div class="card-header bg-light">
					<div class="row">
						<div class="col-8 text-left">
							${rating.movieName}&nbsp;<small>${rating.directorName}</small>
						</div>
						<div class="col-4 text-right">
							종합<span style="color: red;">${rating.totalScore}</span>
						</div>
					</div>
				</div>
				<div class="card-body">
					<h5 class="card-title">
						${rating.ratingTitle}<small>&nbsp;${rating.userID}</small>
					</h5>
					<p class="card-text">${rating.ratingContent}</p>
					<div class="row">
						<div class="col-9 text-center">
							몰입도<span style="color: red;">${rating.immersionScore}</span> 영상미<span
								style="color: red;">${rating.visualbeautyScore}</span> 메시지<span style="color: red;">${rating.messageScore}</span>
							<span style="color: green;"><!-- (추천: ${rating.likeCount}) --></span>
						</div>
						<div class="col-3 text-right">
							<a onclick="return confirm('추천하시겠습니까?')"
								href="./likeAction.jsp?ratingID="></a> <a
								onclick="return confirm('삭제하시겠습니까?')"
								href="./deleteAction.jsp?ratingID="></a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						더 수어사이드 스쿼드&nbsp;<small>제임스 건</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">B</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					이번에도 자막 실망임! &nbsp;<small>pika****</small>
				</h5>
				<p class="card-text">약빤 척하는게 아니라 제대로 하고싶은대로 한 영화. 참신한 장면들이 많았음에도
					역시 대중들에게 이질감이란 방해물이 되기도 하나보다..난 정말 좋았음. 개성있는 캐릭터들에 약간의 주제의식이 첨가되니
					액션을 뒷받침하기에 충분하다.절대적인 것이 있을까? 생각해본다. 누구나 목적을 가질 수 있다.</p>
				<div class="row">
					<div class="col-9 text-left">
						몰입도<span style="color: red;">☆★★★★</span> 영상미<span
							style="color: red;">☆★★★★</span> 메시지<span style="color: red;">☆★★★★</span>
						<span style="color: green;">(추천: 77)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?ratingID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?ratingID=">삭제</a>
					</div>
				</div>
			</div>
		</div>
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						극장판 도라에몽: 진구의 신공룡&nbsp;<small>이마이 카즈아키</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					애들 보여주러 갔다가, 본인이 더 재미지게 보고 왔네요.&nbsp;<small>evee****</small>
				</h5>
				<p class="card-text">이번 극장판은 초반과 중반에는 재미가 있고후반에는 감동과 반전이
					있었네요~?특히, 「너와 거듭한 독백(君と重ねたモノロ?グ)」이 흘러나오는 부분부터는 노비타(진구)와 큐의 우정 이 더 잘
					드러나 가지고 더 감동적으로 봤네요~</p>
				<div class="row">
					<div class="col-9 text-left">
						몰입도<span style="color: red;">★★★★★</span> 영상미<span
							style="color: red;">★★★★★</span> 메시지<span style="color: red;">★★★★★</span>
						<span style="color: green;">(추천: 125)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?ratingID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?ratingID=">삭제</a>
					</div>
				</div>
			</div>
		</div>
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						모가디슈&nbsp;<small>류승완</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">A</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					백기 장면;;;&nbsp;<small>gobu****</small>
				</h5>
				<p class="card-text">누구나 예상할 수 있는 전개가 연속되더니 끝나버림...하나 예상못한 사실,
					21년도에 북한과의 화합장려 영화가 등장할 줄은.. 예상못함..</p>
				<div class="row">
					<div class="col-9 text-left">
						몰입도<span style="color: red;">★★★★★</span> 영상미<span
							style="color: red;">★★★★★</span> 메시지<span style="color: red;">★★★★★</span>
						<span style="color: green;">(추천: 99)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?ratingID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?ratingID=">삭제</a>
					</div>
				</div>
			</div>
		</div>
		<div class="card bg-light mt-3">
			<div class="card-header bg-light">
				<div class="row">
					<div class="col-8 text-left">
						더 그레이트 샤크&nbsp;<small>마틴 윌슨</small>
					</div>
					<div class="col-4 text-right">
						종합<span style="color: red;">C</span>
					</div>
				</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">
					이 영화의 최대 문제는 공포 영화임에도 전혀 무섭지가 않다는 점&nbsp;<small>pair****</small>
				</h5>
				<p class="card-text">정말 돈이 아까운 허접영화 비상보트 탈출 표류중 일행중 2명이 상어에게
					공격당해 죽었는데 배경이 야간설정이라 어떻게 죽었는지 모두 실루엣으로 처리했고 잘보이는 낮에는 일상대화밀 잔뜩
					지루...내돈 내놔~~</p>
				<div class="row">
					<div class="col-9 text-left">
						몰입도<span style="color: red;">☆☆☆★★</span> 영상미<span
							style="color: red;">☆☆☆★★</span> 메시지<span style="color: red;">☆☆☆★★</span>
						<span style="color: green;">(추천: 25)</span>
					</div>
					<div class="col-3 text-right">
						<a onclick="return confirm('추천하시겠습니까?')"
							href="./likeAction.jsp?ratingID=">추천</a> <a
							onclick="return confirm('삭제하시겠습니까?')"
							href="./deleteAction.jsp?ratingID=">삭제</a>
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
					<form action="rating.do" method="post">
						<div class="form-row">
							<div class="form-group col-sm-6">
								<label>영화명</label> <input type="text" name="movieName"
									class="form-control" maxlength="20">
							</div>
							<div class="form-group col-sm-6">
								<label>영화감독</label> <input type="text" name="directorName"
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
									<option value="★★★★★">★★★★★</option>
									<option value="☆★★★★">☆★★★★</option>
									<option value="☆☆★★★" selected>☆☆★★★</option>
									<option value="☆☆☆★★">☆☆☆★★</option>
									<option value="☆☆☆☆★">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>몰입도</label> <select name="immersionScore"
									class="form-control">
									<option value="★★★★★">★★★★★</option>
									<option value="☆★★★★">☆★★★★</option>
									<option value="☆☆★★★" selected>☆☆★★★</option>
									<option value="☆☆☆★★">☆☆☆★★</option>
									<option value="☆☆☆☆★">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>영상미</label> <select name="visualbeautyScore"
									class="form-control">
									<option value="★★★★★">★★★★★</option>
									<option value="☆★★★★">☆★★★★</option>
									<option value="☆☆★★★" selected>☆☆★★★</option>
									<option value="☆☆☆★★">☆☆☆★★</option>
									<option value="☆☆☆☆★">☆☆☆☆★</option>
								</select>
							</div>
							<div class="form-group col-sm-3">
								<label>메시지</label> <select name="messageScore"
									class="form-control">
									<option value="★★★★★">★★★★★</option>
									<option value="☆★★★★">☆★★★★</option>
									<option value="☆☆★★★" selected>☆☆★★★</option>
									<option value="☆☆☆★★">☆☆☆★★</option>
									<option value="☆☆☆☆★">☆☆☆☆★</option>
								</select>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">취소</button>
								<button type="submit" class="btn btn-success" 
								onclick="return confirm('상단의 메인을 클릭하시면 글이 등록됩니다.')">등록하기</button>
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
	<footer class="bg-secondary mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2021 D'movie All Right Reserved. </footer>
	<!-- 제이쿼리 자바스크립트 추가하기 -->
	<script src="../js/jquery.min.js"></script>
	<!-- Popper 자바스크립트 추가하기 -->
	<script src="../js/popper.js"></script>
	<!-- 부트스트랩 자바스크립트 추가하기 -->
	<script src="../js/bootstrap.min.js"></script>
</div>
</body>



</html>