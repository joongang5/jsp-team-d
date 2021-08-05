package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.member.service.ChangeEmailService;
import bbs.member.service.ModifyRequest;
import bbs.member.service.YesOrNoService;
import bbs.mvc.command.CommandHandler;

public class ChangeEmailHandler extends CommandHandler { //������ ����� ����Ű�� ���Դ� ������ ��,,

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
		
		String userKey2 = (String)req.getSession().getAttribute("userKey");
		
		String adminKey = (String)req.getSession().getAttribute("AuthenticationKey");
		String answer = fin.IsSame(adminKey, userKey);
		         
		// System.out.println(adminKey); �ߵ���
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		String to = (String) req.getSession().getAttribute("newEmail2"); //���� ���� �ּ�
		
        if(answer == "yes") {
        ModifyRequest modiReq = new ModifyRequest(userId);
      	 modiReq.setEmail(to);
      	 
      	 
      	 try {
      		 ChangeEmailService.modify(modiReq);
      		 System.out.println("���� ����");
      		 return "/WEB-INF/view/changeEmailSuccess.jsp";
      	 }catch(Exception e){
      		 System.out.println("���� ����");
      	 }
      	
        } else {
        	System.out.println("������ȣ ����ġ");
        }

		
	
		
		return getFormViewName() ;
	}


}
