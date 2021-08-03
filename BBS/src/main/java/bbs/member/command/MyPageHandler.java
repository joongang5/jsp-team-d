package bbs.member.command;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bbs.auth.model.User;

import bbs.member.model.Member;

import bbs.member.service.MemberNotFoundException;


import bbs.member.service.ReadMyPageService;
import bbs.mvc.command.CommandHandler;


public class MyPageHandler extends CommandHandler {

	private ReadMyPageService readService = new ReadMyPageService();


	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myPage.jsp";
	}

	// ������������ �������� �� ������ �����Ϳ� ���� ó���� �մϴ�.
	// ReadArticleHandler�� process(HttpServletRequest req, HttpServletResponse res)
	// �Լ� ����
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 20210802
		
		
		//String memberId ="hyuna";
		Object obj =  req.getSession().getAttribute("authUser");
		User user = (User)obj; 
		String memberId = user.getId();
		
		try {
			Member member = readService.getMember(memberId); //
			req.setAttribute("member", member);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			req.getServletContext().log("no member", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	// ���� �����ϱ�� ���� ó���� �մϴ�.
	// myPage.jsp���� �����ϱ� form�� action�� post������� ó��
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		
		

		
	return null;
	}
}
