package bbs.admin.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.model.BoxOffice;
import bbs.boxoffice.service.RegisterBoxOfficeService;
import bbs.member.service.DuplicateIdException;
import bbs.movie.model.Movie;
import bbs.movie.service.RegisterMoviePosterService;
import bbs.movie.service.RegisterMovieService;
import bbs.mvc.command.CommandHandler;
import bbs.util.api.APIHelper;

public class AdminHandler extends CommandHandler {

	private RegisterBoxOfficeService regBoxOfficeService = new RegisterBoxOfficeService();
	private RegisterMovieService regMovieService = new RegisterMovieService();
	private RegisterMoviePosterService regMoviePosterService = new RegisterMoviePosterService();  
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/admin.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String targetDt = req.getParameter("targetDt");
		String openStartDt = req.getParameter("openStartDt");
		String query = req.getParameter("query");
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		try {
			if (targetDt != null && targetDt.isEmpty() == false) {
				ArrayList<BoxOffice> boxOfficeList = regBoxOfficeService.register(targetDt);
				
				regMoviePosterService.registerBoxOfficePoster(boxOfficeList);
				
				req.setAttribute("registerSuccess", true);	
			}
			
			if (openStartDt != null && openStartDt.isEmpty() == false) {
				ArrayList<Movie> movieList = regMovieService.register(openStartDt);
				
				regMoviePosterService.registerMoviePoster(movieList);
				
				req.setAttribute("registerSuccess", true);
			}
			
			if (query != null && query.isEmpty() == false) {
				APIHelper.naver.requestMovieList(query);
				req.setAttribute("searchSuccess", true);
			}
		} catch (DuplicateIdException e) {
			errors.put("duplicateTargetDt", Boolean.TRUE);
		} catch (RuntimeException e) {
			errors.put("sqlException", Boolean.TRUE);
		}
		
		return getFormViewName();
	}
}
