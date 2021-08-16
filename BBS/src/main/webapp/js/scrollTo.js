var timerId = null;
var panelArr = ['#pageBoxOffice', '#pageReservedMovie'];
var nextPanelIndex = 1;

$(document).ready(function() {
	timerId = setInterval(autoScrollTo, 5000);

	//get all link with class panel
	$('a.panel').click(function () {
		$('a.panel').removeClass('selected');
		$(this).addClass('selected');
		
		var hrefId = $(this).attr('href');
		var selectedIndex = panelArr.indexOf(hrefId);
		
		nextPanelIndex = ++selectedIndex;
		if (nextPanelIndex >= panelArr.length)
			nextPanelIndex = 0;
		
		$('#wrapper').scrollTo($(this).attr('href'), 300);
		
		clearInterval(timerId);
		timerId = setInterval(autoScrollTo, 5000);
						
		return false;
	});
});

function autoScrollTo() {
	for (var i = 0; i < panelArr.length; i++) {
		var panel = getPanel(panelArr[i]);
		if (i == nextPanelIndex) {
			panel.classList.add('selected');
			$('#wrapper').scrollTo(panel.getAttribute('href'), 300);
		} else {
			panel.classList.remove('selected');
		}
	}
	
	nextPanelIndex++;
	if (nextPanelIndex >= panelArr.length)
		nextPanelIndex = 0;
}

function restartAutoScrollTo() {
	autoScrollTo();

	clearInterval(timerId);
	timerId = setInterval(autoScrollTo, 5000);
}

function getPanel(hrefId) {
	var panels = document.getElementsByClassName('panel');
	for (var i = 0; i < panels.length; i++) {
		var value = panels[i].getAttribute('href');
		if (value == hrefId)
			return panels[i];
	}
}