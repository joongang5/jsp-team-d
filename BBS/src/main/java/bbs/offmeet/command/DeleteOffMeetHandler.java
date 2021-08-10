package bbs.offmeet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.offmeet.service.DeleteOffMeetService;
import bbs.util.Util;

public class DeleteOffMeetHandler extends CommandHandler {

	private DeleteOffMeetService deleteService = new DeleteOffMeetService();
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int no = Util.str2Int(req.getParameter("no"));
		User user = (User)req.getSession().getAttribute("authUser");
		
		int result = deleteService.delete(no, user.getId());
		if(result == 1) {
			
			return "list.do";
		}else {
			
			return "read.do";
		}
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}