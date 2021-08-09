package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.boxoffice.model.BoxOffice;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.movie.dao.MoviePosterDao;
import bbs.movie.model.MoviePoster;
import bbs.util.api.APIHelper;

public class RegisterMoviePosterService {

	private MoviePosterDao posterDao = new MoviePosterDao();
	
	public void registerPoster(ArrayList<BoxOffice> boxOfficeList) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			for (BoxOffice boxOffice : boxOfficeList) {
				int count = posterDao.selectCountByMovieCd(conn, boxOffice.getMovieCd());
				if (count > 0) {
					JdbcUtil.rollBack(conn);
					continue;
				}
				
				String posterLink = findPoster(boxOffice.getMovieNm());
				posterDao.insert(conn, new MoviePoster(boxOffice.getMovieCd(), posterLink));
				conn.commit();
			}
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private String findPoster(String movieNm) {
		ArrayList<NaverMovie> list = APIHelper.naver.requestMovieList(movieNm);
		if (list == null)
			return null;
		
		for (NaverMovie naverMovie : list) {
			String posterLink = naverMovie.getImage(); 
			if (posterLink.isEmpty() == false) {
				return posterLink;
			}
		}
		return null;
	}
}