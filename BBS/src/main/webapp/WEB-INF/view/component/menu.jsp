<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="logo">
		<img alt="logo" src="/BBS/img/logo.png" onclick="location.href='/BBS/index.do'">
	</div>
	<div id="loginbox">
		<c:choose>
			<c:when test="${sessionScope.authUser ne null }">
				${sessionScope.authUser.name }님 <br/>
				반갑습니다.
				<button onclick="location.href='./myInfo'">My Info</button>
				<button onclick="return logout()">로그아웃</button>
			</c:when>
			<c:otherwise>
				<form action="login.do" method="post">
					<input type="text" name="id" placeholder="아이디" required="required">
					<input type="password" name="password" placeholder="비밀번호" required="required">
					<button>Login</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<div id="menuItem" onclick="menuClick('index')">게시판</div>
	<div id="menuItem" onclick="menuClick('gallery')">갤러리</div>
	<div id="menuItem" onclick="menuClick('notice')">공지사항</div>
	<div id="menuItem" onclick="menuClick('free')">자유게시판</div>
	<div id="menuItem" onclick="menuClick('admin')">관리자</div>
<script type="text/javascript" src="/BBS/js/menu.js"></script>