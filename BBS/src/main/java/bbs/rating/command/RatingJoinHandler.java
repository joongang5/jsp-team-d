package bbs.rating.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.core.command.CommandHandler;

public class RatingJoinHandler extends CommandHandler{

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/ratinguserJoin.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	
	
}
