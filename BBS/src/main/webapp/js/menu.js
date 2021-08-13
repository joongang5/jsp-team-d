$(document).ready(function() {
	var curPath = $(location).attr('pathname');
	var referrer = document.referrer;
	var naviId = getSelectedNaviId();
	
	if (referrer.indexOf(curPath) > 0)
		$(naviId).next().css('display', 'block');
	else
		$(naviId).next().slideDown('fast');
	
	$(naviId).css('border', '1px solid white');
});

function slideDown(obj) {
	var naviId = getSelectedNaviId();
	$(naviId).css('border', 'none');
	$(naviId).next().slideUp('fast');
	
	$(obj).next().slideDown('fast');
	$(obj).css('border', '1px solid white');
}

function getSelectedNaviId() {
	var curPath = $(location).attr('pathname');
	var referrer = document.referrer;
	var naviId;
	var paths = curPath.split('/');
	if (paths.length > 2)
		naviId = '#' + paths[2];

	if (naviId == '#')
		naviId = '#boxOffice'; 
	
	return naviId;
}

function menuClick(menu) {
	location.href='/BBS/' + menu + '.do';
}

function logout() {
	if (confirm("로그아웃 하시겠습니까?")) {
		location.href="logout.do";
	}
}