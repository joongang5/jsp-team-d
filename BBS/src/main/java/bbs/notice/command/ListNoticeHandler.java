package bbs.notice.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;
import bbs.notice.model.Notice;
import bbs.notice.service.ListNoticeService;

public class ListNoticeHandler extends CommandHandler {

	private ListNoticeService listService = new ListNoticeService();
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	// 여기서 get요청을 처리한다 (보통은)
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
	
	// 여기서 post요청을 처리한다
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	//그런데 굳이 get과 post를 구분할 필요가 없다면
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 여기에서 공통적으로 처리한다.
		
		// 얘는 여기서 작업해야함
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		List<Notice> list = listService.getNotice();
		req.setAttribute("list", list);
		return "/WEB-INF/view/listNotice.jsp";
	}
}
