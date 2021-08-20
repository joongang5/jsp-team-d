<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<style type="text/css">
#joinbox {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
}

#snsUserBox {
	visibility: hidden;
}

#text_align {
	text-align: center;
}

#snsJoinMsg {
	font-size: 12px;
}

#snsJoinErr {
	font-size: 11px;
}

.join_input {
	border: none;
	color: white;
	border-bottom: 2px solid #D1D1D4;
	background: #212121;
	padding: 15px;
	padding-left: 24px;
	margin-left: 18px;
	font-weight: 700;
	width: 75%;
	transition: .4s;
}

.join_input:active, .join_input:focus, .join_input:hover {
	background: #212121;
	outline: none;
	border-bottom-color: #6A679E;
}

#joinSubmit {
	background: #fff;
	font-size: 14px;
	margin-top: 30px;
	margin-left: 22px;
	padding: 8px 20px;
	border-radius: 5px;
	border: 1px solid #D4D3E8;
	text-transform: uppercase;
	text-align: center;
	font-weight: 1000;
	align-items: center;
	width: 85%;
	color: #4C489D;
	box-shadow: 0px 2px 2px #5C5696;
	cursor: pointer;
	transition: .4s;
	float: left;
}

#joinSubmit:active, #joinSubmit:focus, #joinSubmit:hover {
	border-color: #6A679E;
	outline: none;
}

#joinConfirm {
	background: #fff;
	font-size: 14px;
	margin-top: 30px;
	margin-left: 22px;
	padding: 8px 20px;
	border-radius: 5px;
	border: 1px solid #D4D3E8;
	text-transform: uppercase;
	text-align: center;
	font-weight: 1000;
	align-items: center;
	width: 85%;
	color: #4C489D;
	box-shadow: 0px 2px 2px #5C5696;
	cursor: pointer;
	transition: .4s;
	float: left;
}

#joinConfirm:active, #joinConfirm:focus, #joinConfirm:hover {
	border-color: #6A679E;
	outline: none;
}
</style>
<script type="text/javascript">
	function focusName() {
		$("#snsJoinMsg").text("특수문자는 사용하실 수 없습니다.");
	}

	function focusBirth() {
		$("#snsJoinMsg").text("2020-02-02 형식으로 입력해주세요.");
	}

	function joinConfirm() {
		var nD = $("#joinNameDummy").val();
		var bD = $("#joinBirthDummy").val();

		if (nD == "true" && bD == "true") {
			$("#joinSubmit").prop("type", "submit");
			$("#joinConfirm").prop("type", "hidden");
			$(".join_input").prop("readonly", "true");
			$("#snsJoinErr").text("가입하기를 눌러주세요.");
		} else {
			$("#joinSubmit").prop("type", "hidden");
			$("#joinConfirm").prop("type", "submit");
			$("#snsJoinErr").text("입력하신 정보를 다시 확인해주세요.");
		}

	}

	function checkName() {
		var name = $("#name").val();
		var agent = navigator.userAgent.toLowerCase();

		if (name == "") {
			$("#name").css("border-bottom-color", "red");
			$("#snsJoinErr").text("닉네임을 작성해주세요.");
			$("#name").focus();
			$("#joinNameDummy").val("false");
		}
		$.ajax({
			type : 'post',
			dataType : 'text',
			data : 'name=' + name,
			url : '/BBS/joinCheck',
			success : function(rData, textStatus, xhr) {
				if (rData == 1) {
					$("#name").css("border-bottom-color", "red");
					$("#snsJoinErr").text("이미 등록된 닉네임 입니다.");
					$("#joinNameDummy").val("false");
				} else {
					$("#name").css("border-bottom-color", "#6A679E");
					$("#snsJoinErr").text(" ");
					$("#joinNameDummy").val("true");
					return true;
				}
			},
			error : function(xhr, status, e) {
				alert("문제 발생 : " + e);
			}
		});
	}

	function checkBirth() {
		var birth = $("#birth_date").val();
		var bc = document.getElementById('birthConfirm');

		if (birth != null) {
			$("#birth_date").css("border-bottom-color", "#6A679E");
			$("#snsJoinErr").text(" ");
			$("#joinBirthDummy").val("true");
			return true;
		} else if (birth.include('-') != 2 && birth.length() != 10) {
			$("#birth_date").css("border-bottom-color", "red");
			$("#snsJoinErr").text("생년월일을 다시 확인해주세요.");
			$("#joinBirthDummy").val("false");
		}
	}

	function handleOnInput(e) {
		e.value = e.value.replace(/[^ㄱ-힣a-zA-Z0-9]/ig, '')
	}

	function handleOnEmail(e) {
		e.value = e.value.replace(/[^a-z0-9@.-_]/ig, '')
	}
</script>

</head>
<body>
	<div id="joinBox" class="modalBox">
		<h2 align="center">SNS 회원가입</h2>
		<div id="text_align">
			<span id="snsJoinMsg">D'movie</span>
		</div>
		<div id="text_align">
			<span id="snsJoinErr"> </span>
		</div>
		<c:if test="${empty snsUser}">
			<div>잘못된 접근입니다.</div>
		</c:if>

		<c:if test="${! empty snsUser}">
			<form action="${pageContext.request.contextPath }/join.do"
				method="post" autocomplete="off">
				<div>
					<input type="text" id="name" name="name" class="join_input"
						placeholder="닉네임" required="required" onchange="checkName()"
						oninput="handleOnInput(this)" onfocus="focusName()">
				</div>

				<div>
					<input type="date" id="birth_date" name="birth_date"
						class="join_input" placeholder="생일" required="required"
						onchange="checkBirth()" onfocus="focusBirth()">
				</div>

				<input type="hidden" id="joinSubmit" name="joinSubmit" value="가입하기">
			</form>
			<input type="submit" id="joinConfirm" name="joinConfirm" value="확인하기"
				onclick="joinConfirm()">
		</c:if>
	</div>
</body>
<input type="hidden" id="joinNameDummy" value="">
<input type="hidden" id="joinBirthDummy" value="">

</html>