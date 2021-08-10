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

	private BoxOfficeDao<BoxOffice> dao = new BoxOfficeDao<BoxOffice>("box_office_view", null); 

	public ArrayList<BoxOffice> register(String targetDt) {
		ArrayList<BoxOffice> list = APIHelper.kobis.requestBoxOffice(targetDt);
		if (list == null)
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
			for (BoxOffice boxOffice : list) {
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
		return list;
	}
}
