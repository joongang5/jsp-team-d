package bbs.member.command;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;

import bbs.member.model.Member;

import bbs.member.service.MemberNotFoundException;

import bbs.member.service.ReadMyPageService;
import bbs.member.service.ValidEmailService;

import bbs.mvc.command.CommandHandler;


public class MyPageHandler extends CommandHandler { //현

	private ReadMyPageService readService = new ReadMyPageService();
	private ValidEmailService validService = new ValidEmailService();

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

	// 정보 수정하기와 같은 처리를 합니다.
	// myPage.jsp에서 수정하기 form의 action을 post방식으로 처리
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		
		//메일 인증 후 수정 구현
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		String to = req.getParameter("newEmail"); //메일 받을 주소
		//System.out.println(to); 잘 들어옴
		
	 //  validService.validEmailService(to) = 이메일 인증번호 보내는 서비스 
		

		HttpSession keyWasSaved = req.getSession(); //세션에 저장
        keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); //이름 지정
        keyWasSaved.setAttribute("newEmail2", to);
         
		
		         
         return  getFormViewName();
        
			}
		

		
	
	}

