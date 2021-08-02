package bbs.mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CommandHandler {

	protected abstract String getFormViewName();
	
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return getFormViewName();
	}
	
	protected abstract String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
