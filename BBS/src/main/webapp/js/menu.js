function menuClick(menu) {
	location.href='/BBS/' + menu + '.do';
}

function logout() {
	if (confirm("로그아웃 하시겠습니까?")) {
		location.href="logout.do";
	}
}