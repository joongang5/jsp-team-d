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
		<c:if test="${registerSuccess ne null }">
			등록에 성공했습니다.
		</c:if>
	</div>
</body>
</html>