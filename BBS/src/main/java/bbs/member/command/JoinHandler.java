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
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String email = req.getParameter("email");
		String birth_date = req.getParameter("birth_date");
		

//		name = name.replaceAll("<", "&lt;");
//		name = name.replaceAll(">", "&gt;");
//		name = name.replaceAll("/", "&#47;");
		
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(id);
		joinReq.setName(name);
		joinReq.setPassword(password);
		joinReq.setConfirmPassword(confirmPassword);
		joinReq.setEmail(email);
		joinReq.setBirthDate(birth_date);
		
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
	
	
	

//	
//	protected String snsProcessSubmit(HttpServletRequest req, HttpServletResponse res) {
//		JoinRequest joinReq = new JoinRequest();
//		joinReq.setName(req.getParameter("name"));
//		joinReq.setEmail(req.getParameter("email"));
//		
//		Map<String, Boolean> errors = new HashMap<String, Boolean>();
//		req.setAttribute("errors", errors);
//
//		joinReq.validate(errors);
//		
//		if (errors.isEmpty() == false) {
//			return getFormViewName();
//		}
//		
//		try {
//			joinService.join(joinReq);
//			return "/WEB-INF/view/joinSuccess.jsp";
//		} catch (DuplicateIdException e) {
//			errors.put("duplicateId", Boolean.TRUE);
//			return getFormViewName();
//		}
//	}
	
}
