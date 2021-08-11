package bbs.member.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;
import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import bbs.member.service.ChangeEmailService;
import bbs.member.service.ChangePasswordService;
import bbs.member.service.MemberNotFoundException;
import bbs.member.service.ModifyRequest;
import bbs.member.service.ReadMyPageService;
import bbs.member.service.SetNewPasswordService;
import bbs.member.service.ValidEmailService;
import bbs.member.service.YesOrNoService;
import bbs.mvc.command.CommandHandler;

public class SetNewPasswordHandler extends CommandHandler { // 유저가 등록한 인증키가 들어왔다 나가는 곳

	private ReadMyPageService readService = new ReadMyPageService();
	private ValidEmailService validService = new ValidEmailService();
	private SetNewPasswordService newPasswordService = new SetNewPasswordService();

	@Override
	protected String getFormViewName() {
		return "/boxOffice/list.do";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		
		
		if(req.getParameter("newPw") != null) {
			String userId = user.getId();
			String newPw = req.getParameter("newPw");
			newPasswordService.setNewPassword(userId, newPw);
		}
		
		YesOrNoService fin = new YesOrNoService();

		String userKey = req.getParameter("pwChangeKey");
		req.getSession().setAttribute("userKey", userKey);

		String adminKey = (String) req.getSession().getAttribute("AuthenticationKey");
		String answer = fin.IsSame(adminKey, userKey);
		
		//System.out.println(adminKey);

				
		
		if (answer == "yes") {
			return "/WEB-INF/view/setNewOne.jsp";
			
		}

		return getFormViewName();
	}

}
