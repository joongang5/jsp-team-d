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


public class MyPageHandler extends CommandHandler { 

	private ReadMyPageService readService = new ReadMyPageService();
	private ValidEmailService validService = new ValidEmailService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myPage.jsp";
	}

	
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

	
	
		// 20210802
		
		
		//String memberId ="hyuna";
		Object obj =  req.getSession().getAttribute("authUser");
		User user = (User)obj; 
		String memberId = user.getId();
		
		try {
			Member member = readService.getMember(memberId); 
			req.setAttribute("member", member);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			req.getServletContext().log("no member", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}


	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		
	
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		String to = req.getParameter("newEmail"); // 사용자가 입력한 이메일을 가져온다.
		//System.out.println(to); 
		
	 //  validService.validEmailService(to) = 사용자가 입력한 이메일로 인증메일 보내기
		

		HttpSession keyWasSaved = req.getSession(); //
        keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); // 인증 키 저장하기
        keyWasSaved.setAttribute("newEmail2", to);
         
		
		         
         return  "/WEB-INF/view/sendKeySuccess.jsp";
        
			}
		

		
	
	}

