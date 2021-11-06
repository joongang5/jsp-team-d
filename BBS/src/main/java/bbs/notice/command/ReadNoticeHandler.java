package bbs.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.comment.dao.CommentViewDao;
import bbs.comment.model.CommentView;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.notice.service.NoticeContentNotFoundException;
import bbs.notice.service.NoticeData;
import bbs.notice.service.NoticeNotFoundException;
import bbs.notice.service.ReadNoticeService;
import bbs.util.Util;
import dev.core.command.CommandHandler;

public class ReadNoticeHandler extends CommandHandler {

	private ReadNoticeService readService = new ReadNoticeService();
	private PageListService<CommentView> commentListService; 

	public ReadNoticeHandler() {
		CommentViewDao<CommentView> commentDao = new CommentViewDao<CommentView>("comment_notice_view"); 
		commentListService = new PageListService<CommentView>(commentDao);
	}
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readNotice.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int noticeNum = Integer.parseInt(noVal);
		int commentPageNo = Util.str2Int2(req.getParameter("commentPageNo"));
		if (commentPageNo == 0)
			commentPageNo = 1;
		
		try {
			NoticeData noticeData = readService.getNotice(noticeNum, true);
			req.setAttribute("noticeData", noticeData);
			
			String condition = String.format("article_no=%d", noticeNum);
			Page<CommentView> commentPage = commentListService.getPage(commentPageNo, condition);
			req.setAttribute("commentPage", commentPage);
			req.setAttribute("pageName", "notice");
			
			return getFormViewName();
		} catch (NoticeNotFoundException | NoticeContentNotFoundException e) {
			req.getServletContext().log("no notice, e");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}

