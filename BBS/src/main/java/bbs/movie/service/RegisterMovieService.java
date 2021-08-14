package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.Movie;
import bbs.util.api.APIHelper;

public class RegisterMovieService {
	
	private MovieDao<Movie> movieDao = new MovieDao<Movie>("movie", null); 
	
	public ArrayList<Movie> register(String openStartDt) {
		ArrayList<Movie> list = APIHelper.kobis.requestMovieList(openStartDt);
		if (list == null)
			return null;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			for (Movie movie : list) {
				int count = movieDao.selectCountByMovieCd(conn, movie.getMovieCd());
				if (count > 0) {
					JdbcUtil.rollBack(conn);
					continue;
				}
				movieDao.insert(conn, movie);
				conn.commit();
			}
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return list;
	}
}
