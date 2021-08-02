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

	// 마이페이지에 진입했을 때 보여줄 데이터에 대한 처리를 합니다.
	// ReadArticleHandler의 process(HttpServletRequest req, HttpServletResponse res) 함수 참조
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Member member = readService.getMember();
		
		return getFormViewName();
	}
	
	// 정보 수정하기와 같은 처리를 합니다.
	// myPage.jsp에서 수정하기 form의 action을 post방식으로 처리
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		return null;
	}
}
