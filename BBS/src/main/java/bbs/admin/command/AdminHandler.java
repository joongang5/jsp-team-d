package bbs.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.service.RegisterBoxOfficeService;
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
		
		boolean registerSuccess = registerService.register(targetDt);
		
		req.setAttribute("registerSuccess", registerSuccess);
		
		return getFormViewName();
	}

}
