package bbs.comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.comment.model.Comment;
import bbs.comment.service.ModifyCommentService;
import bbs.mvc.command.CommandHandler;
import bbs.util.Util;

public class ModifyCommentHandler extends CommandHandler {

	private ModifyCommentService modifyService = new ModifyCommentService(); 
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String content = req.getParameter("content");
		int articleNo = Util.str2Int2(req.getParameter("articleNo"));
		int commentNo = Util.str2Int2(req.getParameter("commentNo"));
		
		User user = (User) req.getSession().getAttribute("authUser");

		content = Util.str2Replace(content);

		Comment comment = new Comment(commentNo, articleNo, user.getId(), content, null);
		modifyService.modify(comment);

		String viewPage = "/notice/read.do?no=" + articleNo;
		
		return viewPage;
	}

}
