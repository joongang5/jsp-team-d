package bbs.member.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;
import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import bbs.member.service.ValidEmailService;
import bbs.mvc.command.CommandHandler;

public class FindUserInfoHandler extends CommandHandler { // 유저가 등록한 인증키가 들어왔다 나가는 곳

	private ValidEmailService validService = new ValidEmailService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/findUserInfo.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {


		return getFormViewName();
	}


	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		// 메일 인증 후 수정 구현
		Connection conn = ConnectionProvider.getConnection();
		String memberId = req.getParameter("id");
		String memberEmail = req.getParameter("id");

		//System.out.println("id : " + memberId);

		MemberDao dao = new MemberDao();
		Member members = dao.selectById(conn, memberId);
		
		
		if (members == null) {
			res.sendRedirect("./boxOffice/list.do?fpwvalue=none");
			return null;
		}
		
		User user = new User(members.getId(), members.getName(), members.getEmail());
		
		Member emembers = dao.selectByEmail(conn, memberEmail);
		if (emembers == null) {
			res.sendRedirect("./boxOffice/list.do?fpwvalue=none");
			return null;
		}
		

		req.getSession().setAttribute("tempAuthUser", user);

		String to = members.getEmail(); // 메일 받을 주소
		//System.out.println(to);

		// validService.validEmailService(to) = 이메일 인증번호 보내는 서비스

		HttpSession keyWasSaved = req.getSession(); // 세션에 저장
		keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); // 이름 지정
		keyWasSaved.setAttribute("newEmail2", to);

		res.sendRedirect("./boxOffice/list.do?fpwvalue=success");
		return null;

	}

}
