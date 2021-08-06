package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.offmeet.dao.OffMeetContentDao;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.OffMeetContent;

public class WriteOffMeetService {
	
	private OffMeetDao offMeetDao = new OffMeetDao();
	private OffMeetContentDao contentDao = new OffMeetContentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			OffMeet offmeet = toOffmeet(req);
			System.out.println(req.getContent());
			OffMeet savedOffMeet = offMeetDao.insert(conn, offmeet, req.getContent());
			if(savedOffMeet == null) {
				throw new RuntimeException("fail to insert offmeet");
			}
//			OffMeetContent content = new OffMeetContent(savedOffMeet.getNumber(),req.getContent());
//			OffMeetContent savedContent = contentDao.insert(conn, content);
//			if(savedContent == null) {
//				throw new RuntimeException("fail to insert offmeet_content");
//			}
			
			conn.commit();
			
			return savedOffMeet.getNumber();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	private OffMeet toOffmeet(WriteRequest req) {
		Date now = new Date();
		return new OffMeet(null, req.getContent(), req.getWriter(), req.getTitle(), now, now, 0);
	}
}
