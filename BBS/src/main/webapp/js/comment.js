$(document).ready(function() {
	$(".modifyButton").click(function() {
		var modifyBox = $(this).parent().parent().next();
		
		if (modifyBox.children('.modifyInput').length) {
			var modifyInput = modifyBox.children('.modifyInput');
		
			var content = modifyInput.children(".content").text();
			var commentNo = modifyInput.children(".commentNo").text();
			var articleNo = modifyInput.children(".articleNo").text();
			var pageName = modifyInput.children(".pageName").text();
			modifyBox.html(
				"<form action='/BBS/comment/modify.do' method='post' class='commentModifyForm'>"
				+"<textarea class='commentTextarea' name='content' style='min-width: 680px'>"+content+"</textarea>"
				+"<input type='hidden' name='commentNo' value='"+commentNo+"'>"
				+"<input type='hidden' name='articleNo' value='"+articleNo+"'>"
				+"<input type='hidden' name='pageName' value='"+pageName+"'>"
				+"<button style='vertical-align:top; width: 50px; height: 32px;'>등록</button>"
				+"<button class='clear1' style='vertical-align:top; width: 50px; height: 32px;'>취소</button>"
				+"</form>");
					
			$(".clear1").click(function() {
				$(this).parent().parent().html(
					'<div class="modifyInput">'
			 		+'<div class="content" style="margin-bottom: 5px;">'+content+'</div>'
			 		+'<div class="commentNo" style="display: none;">'+commentNo+'</div>'
			 		+'<div class="articleNo" style="display: none;">'+articleNo+'</div>'
			 		+'</div>');
			});
		} else {
			var form = modifyBox.children('.commentModifyForm');
			var content = form.children('textarea[name="content"]').val();
			var commentNo = form.children('input[name="commentNo"]').val();
			var articleNo = form.children('input[name="articleNo"]').val();
			
			modifyBox.html(
				'<div class="modifyInput">'
			 	+'<div class="content" style="margin-bottom: 5px;">'+content+'</div>'
			 	+'<div class="commentNo" style="display: none;">'+commentNo+'</div>'
			 	+'<div class="articleNo" style="display: none;">'+articleNo+'</div>'
			 	+'</div>');
		}
	});
});

