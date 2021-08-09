package bbs.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.notice.service.DeleteNoticeService;
import bbs.util.Util;

public class DeleteNoticeHandler extends CommandHandler {

	private DeleteNoticeService deleteService = new DeleteNoticeService();
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int no = Util.str2Int(req.getParameter("no"));
		User user = (User)req.getSession().getAttribute("authUser");
		
		int result = deleteService.delete(no, user.getId());
		System.out.println(result);
		if(result == 1) {
			//정상 삭제
			return "list.do";
		}else {
			//삭제 실패
			return "read.do";
		}
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
