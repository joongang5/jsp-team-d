package bbs.message.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;



public class MessageHandler extends CommandHandler {

	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/messagelist.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		return null;

	}
}
