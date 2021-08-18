package bbs.comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.comment.service.DeleteCommentService;
import bbs.mvc.command.CommandHandler;
import bbs.util.Util;

public class DeleteCommentHandler extends CommandHandler {

	private DeleteCommentService deleteService = new DeleteCommentService();
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int commentNo = Util.str2Int(req.getParameter("commentNo"));
		int noticeNo = Util.str2Int(req.getParameter("noticeNo"));
		
		deleteService.delete(commentNo);	

		String viewPage = "/notice/read.do?no=" + noticeNo;
		
		return viewPage;
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
