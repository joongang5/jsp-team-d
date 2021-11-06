package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.service.ArticleNotFoundException;
import bbs.movie.dao.MovieViewDao;
import bbs.movie.model.MovieView;
import dev.jdbc.ConnectionProvider;

public class ReadMovieService {

	private MovieViewDao dao = new MovieViewDao();
	
	public MovieView getMovie(int movieCd) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			MovieView movie = dao.selectById(conn, movieCd);
			if (movie == null) {
				throw new ArticleNotFoundException();
			}
			
			return movie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
