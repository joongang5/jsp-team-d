package bbs.article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.model.Writer;
import bbs.article.service.WriteArticleService;
import bbs.article.service.WriteRequest;
import bbs.auth.model.User;
import dev.core.command.CommandHandler;

public class WriteArticleHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/newArticleForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (errors.isEmpty() == false)
			return getFormViewName();
		
		WriteArticleService writeService = new WriteArticleService();
		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		return "/WEB-INF/view/newArticleSuccess.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
				
	}
}
