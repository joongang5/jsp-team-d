package bbs.movie.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticleContentNotFoundException;
import bbs.article.service.ArticleData;
import bbs.article.service.ArticleNotFoundException;
import bbs.movie.model.Movie;
import bbs.movie.service.ReadMovieService;
import bbs.mvc.command.CommandHandler;

public class ReadMovieHandler extends CommandHandler {

	private ReadMovieService readService = new ReadMovieService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readMovie.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int no = 1;
		if (noVal != null) {
			no = Integer.parseInt(noVal);
		}
		
		try {
			Movie movie = readService.getMovie(no);
			req.setAttribute("movie", movie);
			return getFormViewName();
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no movie", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
