var timerId = null;

$(document).ready(function() {
	timerId = setInterval(scrollTo, 5000);

	//get all link with class panel
	$('a.panel').click(function () {
		$('a.panel').removeClass('selected');
		$(this).addClass('selected');
		
		$('#wrapper').scrollTo($(this).attr('href'), 300);
		
		clearInterval(timerId);
		timerId = setInterval(scrollTo, 5000);
						
		return false;
	});
});

function scrollTo() {
	var panels = document.getElementsByClassName('panel');
	for (var i = 0; i < panels.length; i++) {
		var panel = panels.item(i);
		if (panel.classList.contains('selected')) {
			panel.classList.remove('selected');
		} else {
			panel.classList.add('selected');
			$('#wrapper').scrollTo(panel.getAttribute('href'), 300);		
		}
	}
}