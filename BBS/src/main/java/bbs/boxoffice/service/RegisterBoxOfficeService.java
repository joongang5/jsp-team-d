package bbs.boxoffice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.boxoffice.dao.BoxOfficeDao;
import bbs.boxoffice.model.BoxOffice;
import bbs.member.service.DuplicateIdException;
import bbs.movie.model.BaseMovie;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class RegisterBoxOfficeService {

	private BoxOfficeDao<BoxOffice> dao = new BoxOfficeDao<BoxOffice>("box_office", null); 

	public ArrayList<BaseMovie> register(String targetDt, ArrayList<BoxOffice> boxOfficeList) {
		if (boxOfficeList == null)
			return null;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = dao.selectCountByTargetDt(conn, targetDt);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			
			for (BoxOffice boxOffice : boxOfficeList) {
				boxOffice.setTargetDt(targetDt);
				dao.insert(conn, boxOffice);
			}
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		ArrayList<BaseMovie> baseMovieList = new ArrayList<BaseMovie>();
		for (BoxOffice boxOffice : boxOfficeList) {
			baseMovieList.add(boxOffice);
		}
		
		return baseMovieList;
	}
}
