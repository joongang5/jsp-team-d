package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.offmeet.dao.PageOffMeetDao;
import bbs.offmeet.model.OffMeet;
import dev.core.command.CommandHandler;


public class ListMyOffMeetHandler extends CommandHandler {

	private PageListService<OffMeet> listService;
	
	public ListMyOffMeetHandler() {
		String tableName = "offmeet_view";
		String orderRule = "offmeet_no DESC";
		PageOffMeetDao<OffMeet> dao = new PageOffMeetDao<OffMeet>(tableName, orderRule);
		listService = new PageListService<OffMeet>(dao);
	}
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		User user = (User)req.getSession().getAttribute("authUser");
		String condition = String.format("writer_id='%s'", user.getId());
		Page<OffMeet> page = listService.getPage(pageNo, condition);
		req.setAttribute("page", page);
		
		return "/WEB-INF/view/listMyOffMeet.jsp";
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
