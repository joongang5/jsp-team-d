package bbs.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;
import bbs.notice.service.NoticeContentNotFoundException;
import bbs.notice.service.NoticeData;
import bbs.notice.service.NoticeNotFoundException;
import bbs.notice.service.ReadNoticeService;

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
		String noVal = req.getParameter("no");
		int noticeNum = Integer.parseInt(noVal);
		try {
			NoticeData noticeData = readService.getNotice(noticeNum, true);
			req.setAttribute("noticeData", noticeData);
			return getFormViewName();

		} catch (NoticeNotFoundException | NoticeContentNotFoundException e) {
			req.getServletContext().log("no notice, e");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}

