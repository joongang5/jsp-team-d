package bbs.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.auth.service.HashService;
import bbs.member.dao.MemberDao;
import bbs.member.service.ChangePasswordService;
import bbs.member.service.InvalidPasswordException;
import bbs.member.service.MemberNotFoundException;
import bbs.util.ErrorUtil;
import dev.core.command.CommandHandler;

public class ChangePasswordHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/changePwForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

			
		User user = (User) req.getSession().getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

//		String curPw = req.getParameter("curPw");
//		String newPw = req.getParameter("newPw");

		MemberDao dao = new MemberDao();
		String tempcurPw = req.getParameter("curPw");
		String tempnewPw = req.getParameter("newPw");

//		System.out.println(tempcurPw);
//		System.out.println(tempnewPw);

		String prehexcurPw = HashService.stringToHex(tempcurPw);
		String prehexnewPw = HashService.stringToHex(tempnewPw);

		byte[] hexcurPw = HashService.hexStringToByteArray(prehexcurPw);
		byte[] hexnewPw = HashService.hexStringToByteArray(prehexnewPw);

		String salt = HashService.setSalt(user.getId());
		String oldSalt = dao.getSaltById(user.getId());
		String curPw = HashService.Hashing(hexcurPw, oldSalt);
		String newPw = HashService.Hashing(hexnewPw, salt);

//		System.out.println(oldSalt);
//		System.out.println(curPw);
//		System.out.println(newPw);

		ErrorUtil.checkEmpty(errors, curPw, "curPw");
		ErrorUtil.checkEmpty(errors, newPw, "newPw");

		if (errors.isEmpty() == false) {
			return getFormViewName();
		}

		try {
			ChangePasswordService service = new ChangePasswordService();
			service.changePassword(user.getId(), curPw, newPw, salt);
			res.sendRedirect(req.getContextPath() + "/myPage.do?mypwchg=success");
			return null;
		} catch (InvalidPasswordException e) {
			errors.put("badCurPw", Boolean.TRUE);
			res.sendRedirect(req.getContextPath() + "/myPage.do?mypwchg=dnm");
			return null;
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
