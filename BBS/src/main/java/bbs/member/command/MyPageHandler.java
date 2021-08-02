package bbs.member.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bbs.member.model.Member;
import bbs.member.service.MemberNotFoundException;
import bbs.member.service.ModifyMyPageService;
import bbs.member.service.ModifyRequest;
import bbs.member.service.ReadMyPageService;
import bbs.mvc.command.CommandHandler;

public class MyPageHandler extends CommandHandler {

	private ReadMyPageService readService = new ReadMyPageService();
	private ModifyMyPageService modifyService = new ModifyMyPageService();

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
		String memberId = "hyuna"; // ���Ƿ� �� ���̵� ����ֱ�

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
		//20210803
		
		ModifyRequest modifyReq = new ModifyRequest();
		
		modifyReq.setPassword(req.getParameter("password"));
	//	modifyReq.setEmail(req.getParameter("email"));
		

		//���̴� ��й�ȣ required�� ���ѳ���,, �ϴ�

		
		try {
			//modifyService.modify(modifyReq);
			return "/WEB-INF/view/myInfoModifySuccess.jsp";
		} catch (Exception e) {
			System.out.println("���� ����");
			return getFormViewName();
		}
	}
}
