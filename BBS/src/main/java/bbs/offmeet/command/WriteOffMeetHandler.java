package bbs.offmeet.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;
import bbs.offmeet.service.WriteOffMeetService;
import bbs.offmeet.service.WriteRequest;
import bbs.offmeet.model.Writer;
import bbs.auth.model.User;
import bbs.member.service.MyPointService;

public class WriteOffMeetHandler extends CommandHandler {

	//이현아 추가
		private MyPointService myPointS = new MyPointService();
		////////////////////////////////
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/newOffMeetForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		//이현아가 아이디 변수 추가
				String userId = user.getId();
		///////////////////////////////////////		
		
		if (errors.isEmpty() == false)
			return getFormViewName();
		
		WriteOffMeetService writeService = new WriteOffMeetService();
		int newOffMeetNo = writeService.write(writeReq);
		req.setAttribute("newOffMeetNo", newOffMeetNo);
		
		//이현아 추가 - 글 쓰기 완료 했으니 포인트 추가
				myPointS.upgradeMyPoint(userId); 
				/////////////////////////////////////
		
		return "/WEB-INF/view/newOffMeetSuccess.jsp";
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
				
	}
}
