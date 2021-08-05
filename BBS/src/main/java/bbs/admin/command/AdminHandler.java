package bbs.admin.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.service.RegisterBoxOfficeService;
import bbs.member.service.DuplicateIdException;
import bbs.mvc.command.CommandHandler;

public class AdminHandler extends CommandHandler {

	private RegisterBoxOfficeService registerService = new RegisterBoxOfficeService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/admin.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String targetDt = req.getParameter("targetDt");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		try {
			registerService.register(targetDt);
			req.setAttribute("registerSuccess", true);
		} catch (DuplicateIdException e) {
			errors.put("duplicateTargetDt", Boolean.TRUE);
		} catch (RuntimeException e) {
			errors.put("sqlException", Boolean.TRUE);
		}
		
		return getFormViewName();
	}
}
