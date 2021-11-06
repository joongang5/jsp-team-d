package bbs.message.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.core.command.CommandHandler;

public class WriteMessageHandler extends CommandHandler{

	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/writeMessage.jsp";
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String guestName = req.getParameter("guestName");
		String password = req.getParameter("password");
		String message = req.getParameter("message");
		
		req.setAttribute("guestName", guestName);
		req.setAttribute("password", password);
		req.setAttribute("message", message);
		
		return getFormViewName();
	}
}
