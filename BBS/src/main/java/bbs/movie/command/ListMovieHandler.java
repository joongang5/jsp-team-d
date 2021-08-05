package bbs.movie.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.dao.BoxOfficeDao;
import bbs.boxoffice.model.BoxOffice;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.Movie;
import bbs.mvc.command.CommandHandler;

public class ListMovieHandler extends CommandHandler {

	private PageListService<Movie> listService = new PageListService<Movie>(new MovieDao<Movie>());
		
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/listMovie.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		Page<Movie> page = listService.getPage(pageNo);
		req.setAttribute("page", page);
		
		return getFormViewName();
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
