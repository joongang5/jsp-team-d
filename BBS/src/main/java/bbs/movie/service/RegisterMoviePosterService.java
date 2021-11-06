package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.movie.dao.MoviePosterDao;
import bbs.movie.model.BaseMovie;
import bbs.movie.model.MoviePoster;
import bbs.util.api.APIHelper;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class RegisterMoviePosterService {

	private MoviePosterDao posterDao = new MoviePosterDao();
	
	public void registerMoviePoster(ArrayList<BaseMovie> baseMovieList) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			for (BaseMovie baseMovie : baseMovieList) {
				int count = posterDao.selectCountByMovieCd(conn, baseMovie.getMovieCd());
				if (count > 0) {
					JdbcUtil.rollBack(conn);
					continue;
				}
				
				MoviePoster poster = findPoster(baseMovie.getMovieNm());
				if (poster == null)
					continue;
				
				poster.setMovieCd(baseMovie.getMovieCd());
				posterDao.insert(conn, poster);
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

	private MoviePoster findPoster(String movieNm) {
		ArrayList<NaverMovie> list = APIHelper.naver.requestMovieList(movieNm);

		for (NaverMovie naverMovie : list) {
			MoviePoster poster = new MoviePoster(
					null,
					naverMovie.getImage(),
					Float.parseFloat(naverMovie.getUserRating()));
			
			return poster;
		}
		
		return new MoviePoster(null, null, 0f);
	}
}
