package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.member.model.Member;
import bbs.member.service.MemberNotFoundException;
import bbs.member.service.ReadMyOffMeetService;
import bbs.mvc.command.CommandHandler;
import bbs.offmeet.model.OffMeet;

public class MyOffMeetHandler extends CommandHandler { // 20210810 이현아가 생성 
	
	private ReadMyOffMeetService readMyoffMeet = new ReadMyOffMeetService();
	
	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/myOffMeet.jsp";
	}
	
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

		User user = (User) req.getSession().getAttribute("authUser");
		String memberId = user.getId();
		
		try {
			OffMeet offMeet = readMyoffMeet.readMyOffMeet(memberId); //
			req.setAttribute("offMeet", offMeet);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			req.getServletContext().log("no member", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	
	

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
