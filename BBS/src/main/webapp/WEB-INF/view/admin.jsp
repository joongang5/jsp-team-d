<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="admin.do" method="post">
			<div>
				<input type="date" name="targetDt" value="20210801">
			</div>
			<div>
				<input type="submit" value="박스오피스 등록">
			</div>
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
</body>
</html>