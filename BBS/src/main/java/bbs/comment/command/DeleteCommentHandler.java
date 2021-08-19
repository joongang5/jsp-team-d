package bbs.comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.comment.service.DeleteCommentService;
import bbs.mvc.command.CommandHandler;
import bbs.util.Util;

public class DeleteCommentHandler extends CommandHandler {

	private DeleteCommentService deleteService;
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int commentNo = Util.str2Int(req.getParameter("commentNo"));
		int articleNo = Util.str2Int(req.getParameter("articleNo"));
		String pageName = req.getParameter("pageName");

		String tableName = String.format("comment_%s", pageName);
		deleteService = new DeleteCommentService(tableName);
		deleteService.delete(commentNo);	

		String viewPage = String.format("/%s/read.do?no=%d", pageName, articleNo);
		
		return viewPage;
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
