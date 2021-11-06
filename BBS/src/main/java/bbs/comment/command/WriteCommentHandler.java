package bbs.comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.comment.model.Comment;
import bbs.comment.service.WriteCommentService;
import bbs.util.Util;
import dev.core.command.CommandHandler;

public class WriteCommentHandler extends CommandHandler {

	private WriteCommentService writeService;

	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String content = req.getParameter("content");
		int articleNo = Util.str2Int2(req.getParameter("articleNo"));
		User user = (User) req.getSession().getAttribute("authUser");
		String pageName = req.getParameter("pageName");

		content = Util.str2Replace(content);

		Comment comment = new Comment(articleNo, user.getId(), content);
		
		String tableName = String.format("comment_%s", pageName);
		writeService = new WriteCommentService(tableName);
		writeService.write(comment);

		String viewPage = String.format("/%s/read.do?no=%d", pageName, articleNo);
		
		return viewPage;
	}
}
