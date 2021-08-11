package bbs.notice.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.notice.model.Writer;
import bbs.notice.service.WriteNoticeService;
import bbs.notice.service.WriteRequest;

public class WriteNoticeHandler extends CommandHandler {

	private WriteNoticeService writeService = new WriteNoticeService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/newNoticeForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (errors.isEmpty() == false) {
			return getFormViewName();
			
		}
		
	
		int newNoticeNo = writeService.write(writeReq);
		req.setAttribute("newNoticeNo", newNoticeNo);
		
		return "/WEB-INF/view/newNoticeSuccess.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
}
