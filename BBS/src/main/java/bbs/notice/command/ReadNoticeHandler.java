package bbs.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticleData;
import bbs.article.service.ArticleNotFoundException;
import bbs.article.service.ModifyRequest;
import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.notice.service.NoticeContentNotFoundException;
import bbs.notice.service.NoticeData;
import bbs.notice.service.ReadNoticeService;
import bbs.util.PermissionChecker;

public class ReadNoticeHandler extends CommandHandler {

	private ReadNoticeService readService = new ReadNoticeService();

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
		String noVal = req.getParameter("nno");
		int noticeNno = Integer.parseInt(noVal);
		try {
			NoticeData noticeData = readService.getNotice(noticeNno, true);
			req.setAttribute("noticeData", noticeData);
			return getFormViewName();

		} catch (NoticeContentNotFoundException e) {
			req.getServletContext().log("nno notice, e");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return super.process(req, res);
		}
	}
}
