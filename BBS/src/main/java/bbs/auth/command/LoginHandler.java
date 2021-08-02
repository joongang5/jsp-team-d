package bbs.auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.auth.service.LoginFailException;
import bbs.auth.service.LoginService;
import bbs.mvc.command.CommandHandler;
import bbs.util.ErrorUtil;

public class LoginHandler extends CommandHandler {

	private LoginService loginService = new LoginService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/loginForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		ErrorUtil.checkEmpty(errors, id, "id");
		ErrorUtil.checkEmpty(errors, password, "password");
		
		if (errors.isEmpty() == false)	
			return getFormViewName();
		
		try {
			User user = loginService.login(id, password);
			req.getSession().setAttribute("authUser", user);
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			return null;
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return getFormViewName();
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
