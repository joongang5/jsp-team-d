<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> �� ��ȭ�� �Խñ�</title>
</head>
<body>

	�� ��ȭ�� �Խñ�
	<table>
		<tr>
			<td>��ȣ</td>
			<td>${offMeet.offmeetNo}</td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td>${offMeet.writerId}</td>
		</tr>
		<tr>
			<td>����</td>
			<td>${offMeet.title}</td>
		</tr>
		<tr>
			<td>����</td>
			<td>${offMeet.offmeetContent}</td>
		</tr>
		<tr>
			<td>�ø� ��¥</td>
			<td>${offMeet.regDate}</td>
		</tr>
		<tr>
			<td>���� ��¥</td>
			<td>${offMeet.modifiedDate}</td>
		</tr>
		<tr>
			<td>��ȸ��</td>
			<td>${offMeet.readCount}</td>
		</tr>
	</table>
	

</body>
</html>