package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.offmeet.dao.OffMeetDao;

public class DeleteOffMeetService {

	private OffMeetDao offmeetDao = new OffMeetDao();
	
	public int delete(int no, String id) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = OffMeetDao.delete(conn, no, id);
			if(result == 0) {
				throw new RuntimeException("?");
			}
		
			
			conn.commit();
			
			return result;
		} catch (SQLException e){
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
}
}



