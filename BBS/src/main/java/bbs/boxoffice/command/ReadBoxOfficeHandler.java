package bbs.boxoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;

public class ReadBoxOfficeHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readBoxOffice.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
