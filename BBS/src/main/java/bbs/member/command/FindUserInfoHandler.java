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
		
		System.out.println("##################1");
		
		Connection conn = ConnectionProvider.getConnection();
		String memberId = req.getParameter("id");
		
		//id값 가져오기

		MemberDao dao = new MemberDao();
		Member member = dao.selectById(conn, memberId);
		
		//id에 일치하는 회원 있는지 확인

		if (member == null) {
			return getFormViewName();
		}

		String to = member.getEmail();
		
		//있으면 이메일 주소 가져오기
		req.getSession().setAttribute("email", to);
		validService.validEmailService(to);
		
		System.out.println("##################2");
		//이메일로 인증코드 발송
		return  getFormViewName();
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		YesOrNoService fin = new YesOrNoService();
		
		Connection conn = ConnectionProvider.getConnection();
		String memberId = req.getParameter("id");
		
		//id값 가져오기

		MemberDao dao = new MemberDao();
		Member member = dao.selectById(conn, memberId);
		
		String to = member.getEmail();
		
		System.out.println("@@@@@@@@1");
		//HttpSession keyWasSaved = req.getSession(); // 세션에 저장
		//keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); // 이름 지정
		//keyWasSaved.setAttribute("newEmail2", to);
		
		
		System.out.println("@@@@@@@@2");

		String userKey = req.getParameter("emailChangeKey");
		req.getSession().setAttribute("userKey", userKey);
		
		System.out.println("@@@@@@@@3");

		String adminKey = (String) req.getSession().getAttribute("AuthenticationKey");
		String answer = fin.IsSame(adminKey, userKey);

		System.out.println(adminKey);
		System.out.println(userKey);
		System.out.println(answer);
		System.out.println("@@@@@@@@4");
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = member.getId();
		String curPw = member.getPassword();
		String newPw = req.getParameter("newPw");
		
		System.out.println("@@@@@@@@5");

		System.out.println("to : " + to);
		
		//HttpSession keyWasSaved = req.getSession(); //세션에 저장
        //keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); //이름 지정
        
		
		
		
		
		
		

		return getFormViewName();

	}

}
