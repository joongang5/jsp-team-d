package bbs.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.service.DuplicateIdException;
import bbs.member.service.JoinRequest;
import bbs.member.service.JoinService;
import bbs.mvc.command.CommandHandler;

public class JoinHandler extends CommandHandler {

	private JoinService joinService = new JoinService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/joinForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		joinReq.setEmail(req.getParameter("email"));
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		joinReq.validate(errors);
		
		if (errors.isEmpty() == false) {
			return getFormViewName();
		}
		
		try {
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return getFormViewName();
		}
	}
}
