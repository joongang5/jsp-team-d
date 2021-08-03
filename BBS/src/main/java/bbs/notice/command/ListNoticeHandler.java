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
		// ���⿡�� ���������� ó���Ѵ�.
		
		// ��� ���⼭ �۾��ؾ���
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
