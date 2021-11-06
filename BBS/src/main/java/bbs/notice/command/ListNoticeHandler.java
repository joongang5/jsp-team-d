package bbs.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.notice.service.ListNoticeService;
import bbs.notice.service.NoticePage;
import dev.core.command.CommandHandler;

public class ListNoticeHandler extends CommandHandler {

	private ListNoticeService listService = new ListNoticeService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/listNotice.jsp";
	}

	// ���⼭ get��û�� ó���Ѵ� (������)
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
	
	// ���⼭ post��û�� ó���Ѵ�
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	//�׷��� ���� get�� post�� ������ �ʿ䰡 ���ٸ�
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null){
			pageNo = Integer.parseInt(pageNoVal);
		}
		NoticePage noticePage = listService.getNoticePage(pageNo);
		req.setAttribute("noticePage", noticePage);
		return getFormViewName();
	}
}
