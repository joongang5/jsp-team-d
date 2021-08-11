package bbs.member.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;
import bbs.auth.service.LoginService;
import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import bbs.member.service.ChangeEmailService;
import bbs.member.service.ChangePasswordService;
import bbs.member.service.MemberNotFoundException;
import bbs.member.service.ModifyRequest;
import bbs.member.service.ReadMyPageService;
import bbs.member.service.ValidEmailService;
import bbs.member.service.YesOrNoService;
import bbs.mvc.command.CommandHandler;

public class FindUserInfoHandler extends CommandHandler { // 유저가 등록한 인증키가 들어왔다 나가는 곳

	private ReadMyPageService readService = new ReadMyPageService();
	private ValidEmailService validService = new ValidEmailService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/findUserInfo.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

//		Connection conn = ConnectionProvider.getConnection();
//		String memberId = req.getParameter("id");
//
//		System.out.println("##################2" + memberId);
//
//		MemberDao dao = new MemberDao();
//		Member members = dao.selectById(conn, memberId);
//		
//		if (members == null) {
//			return getFormViewName();
//		}
//		
//		// 20210802
//		
//		User user = new User(members.getId(), members.getName(), members.getEmail());
//
//		req.getSession().setAttribute("authUser", user);
//		
//		
//		try {
//			Member member = readService.getMember(memberId); //
//			req.setAttribute("member", member);
//			return getFormViewName();
//		} catch (MemberNotFoundException e) {
//			req.getServletContext().log("no member", e);
//			res.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
		return getFormViewName();
	}

	// 정보 수정하기와 같은 처리를 합니다.
	// myPage.jsp에서 수정하기 form의 action을 post방식으로 처리
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 메일 인증 후 수정 구현
		Connection conn = ConnectionProvider.getConnection();
		String memberId = req.getParameter("id");

		//System.out.println("id : " + memberId);

		MemberDao dao = new MemberDao();
		Member members = dao.selectById(conn, memberId);

		if (members == null) {
			return getFormViewName();
		}

		// 20210802

		User user = new User(members.getId(), members.getName(), members.getEmail());

		req.getSession().setAttribute("authUser", user);

		String to = members.getEmail(); // 메일 받을 주소
		//System.out.println(to);

		// validService.validEmailService(to) = 이메일 인증번호 보내는 서비스

		HttpSession keyWasSaved = req.getSession(); // 세션에 저장
		keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); // 이름 지정
		keyWasSaved.setAttribute("newEmail2", to);

		return getFormViewName();

	}

}
