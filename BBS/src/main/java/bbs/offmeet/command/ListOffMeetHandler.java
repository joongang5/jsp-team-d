package bbs.offmeet.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;
import bbs.offmeet.service.ListOffMeetService;
import bbs.offmeet.service.OffMeetPage;

public class ListOffMeetHandler extends CommandHandler {

	private ListOffMeetService listService = new ListOffMeetService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String pageNoVal = req.getParameter("pageNo");
		
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		OffMeetPage offmeetPage = listService.getOffMeetPage(pageNo);
		req.setAttribute("offmeetPage", offmeetPage);
		return "/WEB-INF/view/listOffMeet.jsp";
	}

	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
