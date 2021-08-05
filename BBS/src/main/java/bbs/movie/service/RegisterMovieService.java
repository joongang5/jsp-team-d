package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.service.DuplicateIdException;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.Movie;
import bbs.util.api.APIHelper;

public class RegisterMovieService {
	private MovieDao<Movie> dao = new MovieDao<Movie>(); 

	public void register(String date) {
		ArrayList<Movie> list = APIHelper.kobis.requestMovieList(date);
		if (list == null)
			return;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = dao.selectCountByMovieCd(conn, date);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			for (Movie movie : list) {
				dao.insert(conn, movie);
			}
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
