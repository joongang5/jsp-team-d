package bbs.boxoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;

public class ListBoxOfficeHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/listBoxOffice.jsp";
	}
	
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String targetDt = req.getParameter("targetDt");
		if (targetDt == null)
			targetDt = "20210808";
		
		return getFormViewName();
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return null;
	}
}
