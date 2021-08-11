package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.member.service.ChangeEmailService;
import bbs.member.service.ModifyRequest;
import bbs.member.service.YesOrNoService;
import bbs.mvc.command.CommandHandler;

public class ChangeEmailHandler extends CommandHandler { //유저가 등록한 인증키가 들어왔다 나가는 곳

	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/changeEmailFail.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		YesOrNoService fin = new YesOrNoService();
		
		String	userKey = req.getParameter("emailChangeKey");
		req.getSession().setAttribute("userKey",userKey);
		
		
		String adminKey = (String)req.getSession().getAttribute("AuthenticationKey");
		String answer = fin.IsSame(adminKey, userKey);
		         
		// System.out.println(adminKey);잘들어옴
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		String to = (String) req.getSession().getAttribute("newEmail2"); //메일 받을 주소
		
        if(answer == "yes") {
        ModifyRequest modiReq = new ModifyRequest(userId);
      	 modiReq.setEmail(to);
      	 
      	 
      	 try {
      		 ChangeEmailService.modify(modiReq);
      		 System.out.println("수정 성공");
      		 return "/WEB-INF/view/changeEmailSuccess.jsp";
      	 }catch(Exception e){
      		 System.out.println("수정 실패");
      	 }
      	
        } else {
        	System.out.println("인증번호 불일치");
        }

		
	
		
		return getFormViewName() ;
	}


}
