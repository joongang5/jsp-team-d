<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet">
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
				<div style="padding: 10px; color: white;">
					<form action="admin.do" method="post">
						<div>
							<input type="date" name="targetDt" placeholder="20210816">
							<label>박스오피스 등록 (해당 날짜)</label>
						</div>
						<div>
							<input type="date" name="openStartDt" placeholder="2021">
							<label>최신영화 100 등록 (해당 년도)</label>
						</div>			
						<input type="submit" value="입력된 값 실행">
						<label>입력된 값 없을 경우 DB검토</label>
					</form>
					<c:if test="${registerSuccess }">
						등록에 성공했습니다.
					</c:if>
					<c:if test="${errors.duplicateTargetDt }">
						이미 등록된 데이터입니다. 
					</c:if>
					<c:if test="${errors.sqlException }">
						SQL문 실행에 실패했습니다.
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>