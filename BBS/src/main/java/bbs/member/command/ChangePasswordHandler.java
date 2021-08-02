package bbs.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.member.service.ChangePasswordService;
import bbs.member.service.InvalidPasswordException;
import bbs.member.service.MemberNotFoundException;
import bbs.mvc.command.CommandHandler;
import bbs.util.ErrorUtil;

public class ChangePasswordHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/changePwForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		String curPw = req.getParameter("curPw");
		String newPw = req.getParameter("newPw");
		
		ErrorUtil.checkEmpty(errors, curPw, "curPw");
		ErrorUtil.checkEmpty(errors, newPw, "newPw");
		
		if (errors.isEmpty() == false) {
			return getFormViewName();	
		}
		
		try {
			ChangePasswordService service = new ChangePasswordService();
			service.changePassword(user.getId(), curPw, newPw);
			return "/WEB-INF/view/changePwSuccess.jsp";
		} catch (InvalidPasswordException e) {
			errors.put("badCurPw", Boolean.TRUE);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
