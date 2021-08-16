package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.service.ArticleNotFoundException;
import bbs.jdbc.ConnectionProvider;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.Movie;

public class ReadMovieService {

	private MovieDao<Movie> dao = new MovieDao<Movie>();
	
	public Movie getMovie(int movieCd) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Movie movie = dao.selectById(conn, movieCd);
			if (movie == null) {
				throw new ArticleNotFoundException();
			}
			
			return movie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
