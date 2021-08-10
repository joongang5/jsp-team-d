package bbs.boxoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.dao.BoxOfficeViewDao;
import bbs.boxoffice.model.BoxOfficeView;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.mvc.command.CommandHandler;

public class ListBoxOfficeHandler extends CommandHandler {
	
    private PageListService<BoxOfficeView> listService;
    
    public ListBoxOfficeHandler() {
    	String tableName = "box_office_view";
    	String orderRule = "rank ASC";
    	BoxOfficeViewDao<BoxOfficeView> dao = new BoxOfficeViewDao<BoxOfficeView>(tableName, orderRule);
    	listService = new PageListService<BoxOfficeView>(dao);
    }
    
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/listBoxOffice.jsp";
	}
	
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String pageNoVal = req.getParameter("pageNo");
        int pageNo = 1;
        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }
        
        String targetDt = req.getParameter("targetDt");
        if (targetDt == null)
            targetDt = "20210808";
        
        Page<BoxOfficeView> page = listService.getPage(pageNo, String.format("target_dt=%s", targetDt));
        req.setAttribute("page", page);
        
		return getFormViewName();
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return null;
	}
}
