package bbs.error.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.core.command.CommandHandler;

public class NullHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
}
