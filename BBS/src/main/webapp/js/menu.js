$(document).ready(function() {
	var curPath = $(location).attr('pathname');
	var referrer = document.referrer;
	
	var curNaviId = getNaviIdFromPath(curPath);
	var referId = getNaviIdFromPath(referrer); 
	
	if (curNaviId == referId)
		$(curNaviId).next().css('display', 'block');
	else
		$(curNaviId).next().slideDown('fast');
	
	$(curNaviId).css('border', '1px solid white');
	
	var curNaviSubId = getNaviSubIdFromPath(curPath);
	if (curNaviSubId.indexOf('.do') == -1) {
		$(curNaviSubId).children(':first').css('color', 'red');		
	} else {
		var firstSubId = curNaviId + 'Sub';
		$(firstSubId).children(':first').css('color', 'red');
	}
});

function slideDown(obj) {
	var naviId = getSelectedNaviId();
	if (naviId != '') {
		$(naviId).css('border', 'none');
		$(naviId).next().slideUp('fast');	
	}
	
	$(obj).next().slideDown('fast');
	$(obj).css('border', '1px solid white');
}

function getSelectedNaviId() {
	var curPath = $(location).attr('pathname');
	return getNaviIdFromPath(curPath);
}

function getNaviIdFromPath(path) {
	if (path.indexOf('http:') != -1)
		path = path.replace('http://localhost:8080', '');

	var split = path.split('/');
	var naviId = '';
	if (split.length > 2)
		naviId = '#' + split[2];
	
	if (naviId == '')
		naviId = '#boxOffice';
		
	return naviId;
}

function getNaviSubIdFromPath(path) {
	var split = path.split('/');
	var naviSubId = '';
	if (split.length > 3)
		naviSubId = '#' + split[3];
	
	return naviSubId;
}

function menuClick(menu) {
	location.href='/BBS/' + menu + '.do';
}

function logout() {
	if (confirm("로그아웃 하시겠습니까?")) {
		location.href="logout.do";
	}
}