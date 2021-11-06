package bbs.notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.notice.dao.NoticeContentDao;
import bbs.notice.dao.NoticeDao;
import bbs.notice.model.Notice;
import bbs.notice.model.NoticeContent;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class WriteNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice = toNotice(req);
			Notice savedNotice = noticeDao.insert(conn, notice);
			if(savedNotice == null) {
				throw new RuntimeException("fail to insert notice");
			}
			NoticeContent content = new NoticeContent(savedNotice.getNumber(), req.getContent());
			NoticeContent savedContent = contentDao.insert(conn, content);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert notice_content");
			}
			
			conn.commit();
			
			return savedNotice.getNumber();
			
			
			
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

	private Notice toNotice(WriteRequest req) {
		Date now = new Date();
		return new Notice(null, req.getWirter(), req.getTitle(), now, now, 0);
	}


}
