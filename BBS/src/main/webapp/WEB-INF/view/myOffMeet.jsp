<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="uft-8">
<title> 내 영화팟 게시글</title>
</head>
<body>

	내 영화팟 게시글
	<table>
		<tr>
			<td>번호</td>
			<td>${offMeet.offmeetNo}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${offMeet.writerId}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${offMeet.title}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${offMeet.offmeetContent}</td>
		</tr>
		<tr>
			<td>올린 날짜</td>
			<td>${offMeet.regDate}</td>
		</tr>
		<tr>
			<td>수정 날짜</td>
			<td>${offMeet.modifiedDate}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${offMeet.readCount}</td>
		</tr>
	</table>
	

</body>
</html>