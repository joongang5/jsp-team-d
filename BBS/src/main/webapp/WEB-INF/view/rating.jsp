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

						<a class="dropdown-item" href="../login.do">로그인</a> <a class="dropdown-item"
							href="../join.do">회원가입</a> <a class="dropdown-item" href="../logout.do">로그아웃</a>

					</div></li>

			</ul>

			<form class="form-inline my-2 my-lg-0">

				<input class="form-control mr-sm-2" type="search"
					placeholder="내용을 입력하세요." aria-label="Search">

				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>

			</form>

		</div>

	</nav>
	<section class="container">
		<form method="get" action="./rating.jsp" class="form-inline mt-3">
			<select name="lectureDivide" class="form-control mx mt-2">
				<option value="전체">전체</option>
				<option value="현재 상영작">현재 상영작</option>
				<option value="지난 개봉작">지난 개봉작</option>
				<option value="기타">기타</option>
			</select>
			<input type="text" name="search" class
		</form>
	</section>
	
	<!-- 제이쿼리 자바스크립트 추가하기 -->
	<script src="../js/jquery.min.js"></script>
	<!-- Popper 자바스크립트 추가하기 -->
	<script src="../js/popper.js"></script>
	<!-- 부트스트랩 자바스크립트 추가하기 -->
	<script src="../js/bootstrap.min.js"></script>

</body>



</html>