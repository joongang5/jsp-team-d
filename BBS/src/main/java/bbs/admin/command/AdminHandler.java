package bbs.admin.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.boxoffice.model.BoxOffice;
import bbs.boxoffice.service.RegisterBoxOfficeService;
import bbs.jdbc.ConnectionProvider;
import bbs.member.service.DuplicateIdException;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.BaseMovie;
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
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		try {
			if (targetDt.isEmpty() == false) {
				ArrayList<BoxOffice> boxOfficeList = APIHelper.kobis.requestBoxOffice(targetDt);
				
				ArrayList<BaseMovie> regList = regBoxOfficeService.register(targetDt, boxOfficeList);
				
				regMoviePosterService.registerMoviePoster(regList);

				regMovieService.register(regList);

				req.setAttribute("registerSuccess", true);
			}
			
			if (openStartDt.isEmpty() == false) {
				ArrayList<BaseMovie> regList = APIHelper.kobis.requestMovieList(openStartDt);

				regMoviePosterService.registerMoviePoster(regList);
				
				regMovieService.register(regList);
				
				req.setAttribute("registerSuccess", true);
			}
			
			if (targetDt.isEmpty() && openStartDt.isEmpty()) {
				try (Connection conn = ConnectionProvider.getConnection()) {
					MovieDao<Movie> movieDao = new MovieDao<Movie>();
					ArrayList<BaseMovie> regList = movieDao.selectAllToBase(conn);

					regMoviePosterService.registerMoviePoster(regList);
					
					regMovieService.register(regList);
					
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (DuplicateIdException e) {
			errors.put("duplicateTargetDt", Boolean.TRUE);
		} catch (RuntimeException e) {
			errors.put("sqlException", Boolean.TRUE);
		}
		
		return getFormViewName();
	}
}
