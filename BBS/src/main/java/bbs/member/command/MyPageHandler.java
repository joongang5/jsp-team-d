package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.model.Member;
import bbs.member.service.ReadMyPageService;
import bbs.mvc.command.CommandHandler;

public class MyPageHandler extends CommandHandler {

	private ReadMyPageService readService = new ReadMyPageService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myPage.jsp";
	}

	// ������������ �������� �� ������ �����Ϳ� ���� ó���� �մϴ�.
	// ReadArticleHandler�� process(HttpServletRequest req, HttpServletResponse res) �Լ� ����
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Member member = readService.getMember();
		
		return getFormViewName();
	}
	
	// ���� �����ϱ�� ���� ó���� �մϴ�.
	// myPage.jsp���� �����ϱ� form�� action�� post������� ó��
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		return null;
	}
}
