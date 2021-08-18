package bbs.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.service.HashService;
import bbs.member.dao.MemberDao;
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
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();

		String id = req.getParameter("id");
		String name = req.getParameter("name");

		MemberDao dao = new MemberDao();
		String tempPassword = req.getParameter("password");
		String prehexPw = HashService.stringToHex(tempPassword);
		byte[] hexPw = HashService.hexStringToByteArray(prehexPw);

		String salt = HashService.setSalt(id);
		String password = HashService.Hashing(hexPw, salt);

//		System.out.println(tempPassword);
//		System.out.println(salt);
//		System.out.println(password);

		String confirmPassword = password;
		// String confirmPassword = req.getParameter("confirmPassword");
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
		joinReq.setSalt(salt);
		joinReq.setBirthDate(birth_date);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		joinReq.validate(errors);

		if (errors.isEmpty() == false) {
			return getFormViewName();
		}

		try {
			joinService.join(joinReq);
			if (session.getAttribute("snsUser") != null)
				session.invalidate();
			res.sendRedirect("./boxOffice/list.do?joinvalue=done");
			return null;
		} catch (DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return getFormViewName();
		}

	}

}
