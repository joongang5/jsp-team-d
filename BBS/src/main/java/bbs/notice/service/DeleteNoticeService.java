package bbs.notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.notice.dao.NoticeContentDao;
import bbs.notice.dao.NoticeDao;

public class DeleteNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao noticeContentDao = new NoticeContentDao();
	
	public int delete(int no, String id) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = noticeDao.delete(conn, no, id);
			System.out.println(result);
			if(result == 0) {
				throw new RuntimeException("fail to delete notice");
			}
			
			result = noticeContentDao.delete(conn, no);
			if(result == 0) {
				throw new RuntimeException("fail to delete notice_content");
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



