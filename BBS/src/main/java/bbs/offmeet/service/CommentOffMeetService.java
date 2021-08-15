package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.offmeet.dao.OffMeetContentDao;
import bbs.offmeet.dao.OffMeetDao2;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.OffMeetContent;

public class CommentOffMeetService {
	
	private OffMeetDao2 OffMeetDao2 = new OffMeetDao2();
	private OffMeetContentDao contentDao = new OffMeetContentDao();
	
	public Integer write(CommentWrite req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			OffMeet offmeet = toOffmeet(req);
			System.out.println(req.getContent());
			OffMeet savedOffMeet = OffMeetDao2.insert(conn, offmeet, req.getContent());
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

	private OffMeet toOffmeet(CommentWrite req) {
		Date now = new Date();
		return new OffMeet(null, req.getContent(), req.getWriter(), req.getTitle(), now, now, 0);
	}
}
