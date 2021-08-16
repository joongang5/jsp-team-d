package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.service.DuplicateIdException;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.BaseMovie;
import bbs.movie.model.Movie;
import bbs.util.api.APIHelper;

public class RegisterMovieService {
	
	private MovieDao<Movie> movieDao = new MovieDao<Movie>("movie", null); 
	
	public void register(String movieCd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = movieDao.selectCountByMovieCd(conn, movieCd);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			
			Movie movie = APIHelper.kobis.requestMovieDetail(movieCd);
			movieDao.insert(conn, movie);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void register(ArrayList<BaseMovie> baseMovieList) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			for (BaseMovie baseMovie : baseMovieList) {
				int count = movieDao.selectCountByMovieCd(conn, baseMovie.getMovieCd());
				if (count > 0) {
					JdbcUtil.rollBack(conn);
					continue;
				}

				Movie movie = APIHelper.kobis.requestMovieDetail(baseMovie.getMovieCd());
				movieDao.insert(conn, movie);
				conn.commit();
				
				Thread.sleep(500);
			}
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
