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

	// 마이페이지에 진입했을 때 보여줄 데이터에 대한 처리를 합니다.
	// ReadArticleHandler의 process(HttpServletRequest req, HttpServletResponse res)
	// 함수 참조
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 20210802
		String memberId = "hyuna"; // 임의로 내 아이디 집어넣기

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

	// 정보 수정하기와 같은 처리를 합니다.
	// myPage.jsp에서 수정하기 form의 action을 post방식으로 처리
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//20210803
		
		ModifyRequest modifyReq = new ModifyRequest();
		
		modifyReq.setPassword(req.getParameter("password"));
	//	modifyReq.setEmail(req.getParameter("email"));
		

		//밤이니 비밀번호 required만 시켜놓고,, 일단

		
		try {
			//modifyService.modify(modifyReq);
			return "/WEB-INF/view/myInfoModifySuccess.jsp";
		} catch (Exception e) {
			System.out.println("수정 실패");
			return getFormViewName();
		}
	}
}
