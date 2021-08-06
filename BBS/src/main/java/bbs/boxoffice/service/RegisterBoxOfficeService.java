package bbs.boxoffice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.boxoffice.dao.BoxOfficeDao;
import bbs.boxoffice.model.BoxOffice;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.service.DuplicateIdException;
import bbs.util.api.APIHelper;

public class RegisterBoxOfficeService {

	private BoxOfficeDao<BoxOffice> dao = new BoxOfficeDao<BoxOffice>(); 

	public void register(String date) {
		ArrayList<BoxOffice> list = APIHelper.kobis.requestBoxOffice(date);
		if (list == null)
			return;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = dao.selectCountByTargetDt(conn, date);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			for (BoxOffice boxOffice : list) {
				boxOffice.setTargetDt(date);
				dao.insert(conn, boxOffice);
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
