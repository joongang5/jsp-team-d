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

	//������ �߰�
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
		
		//�����ư� ���̵� ���� �߰�
				String userId = user.getId();
		///////////////////////////////////////		
		
		if (errors.isEmpty() == false)
			return getFormViewName();
		
		WriteOffMeetService writeService = new WriteOffMeetService();
		int newOffMeetNo = writeService.write(writeReq);
		req.setAttribute("newOffMeetNo", newOffMeetNo);
		
		//������ �߰� - �� ���� �Ϸ� ������ ����Ʈ �߰�
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
