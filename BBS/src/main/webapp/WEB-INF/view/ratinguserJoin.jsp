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
		<a class="navbar-brand" href="index.jsp">D'movie 영화평가 라운지</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="../boxOffice/list.do">MAIN PAGE</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="dropdown"
					data-toggle="dropdown"> JOIN US (추가 계정필요)</a>
					<div class="dropdown-menu" aria-labelledby="dropdown">
						<a class="dropdown-item" href="login.do">로그인</a> <a
							class="dropdown-item" href="join.do">회원가입</a> <a
							class="dropdown-item" href="logout.do">로그아웃</a>
					</div></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="내용을 입력하세요." aria-label="Search">
				<button class="btn btn-outline-info my-2 my-sm-0" type="submit">검색</button>
			</form>
		</div>
	</nav>
	<section class="container mt-3" style="max-width: 560px;">
		<form method="post" action="./ratinguserRegisterAction.jsp">
			<div class="form-group">
				<label>아이디</label> <input type="text" name="userID"
					class="form-control">
			</div>
			<div class="form-group">
				<label>비밀번호</label> <input type="password" name="userPassword"
					class="form-control">
			</div>
			<div class="form-group">
				<label>이메일</label> <input type="email" name="userEmail"
					class="form-control">
			</div>
			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
	</section>
	<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
		Copyright &copy; 2021 D'movie All Right Reserved. </footer>
	<!-- 제이쿼리 자바스크립트 추가하기 -->
	<script src="../js/jquery.min.js"></script>
	<!-- Popper 자바스크립트 추가하기 -->
	<script src="../js/popper.js"></script>
	<!-- 부트스트랩 자바스크립트 추가하기 -->
	<script src="../js/bootstrap.min.js"></script>

</body>
</html>