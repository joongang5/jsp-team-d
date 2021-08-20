package bbs.boxoffice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.dao.BoxOfficeViewDao;
import bbs.boxoffice.model.BoxOfficeView;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.movie.dao.ReservedMovieViewDao;
import bbs.movie.model.ReservedMovieView;
import bbs.mvc.command.CommandHandler;

public class ListBoxOfficeHandler extends CommandHandler {
	
    private PageListService<BoxOfficeView> boxOfficeListService;
    private PageListService<ReservedMovieView> reservedListService;
    
    public ListBoxOfficeHandler() {
    	BoxOfficeViewDao<BoxOfficeView> boxOfficeViewDao = new BoxOfficeViewDao<BoxOfficeView>();
    	boxOfficeListService = new PageListService<BoxOfficeView>(boxOfficeViewDao);
    	

    	ReservedMovieViewDao<ReservedMovieView> reservedMovieDao = new ReservedMovieViewDao<ReservedMovieView>();
    	reservedListService = new PageListService<ReservedMovieView>(reservedMovieDao);
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
            targetDt = "20210819";
        
        Page<BoxOfficeView> boxOfficePage = boxOfficeListService.getPage(pageNo, String.format("target_dt=%s", targetDt));
        req.setAttribute("boxOfficePage", boxOfficePage);
        
        Page<ReservedMovieView> reservedMoviePage = reservedListService.getPage(pageNo);
        req.setAttribute("reservedMoviePage", reservedMoviePage);
        
		return getFormViewName();
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return null;
	}
}
