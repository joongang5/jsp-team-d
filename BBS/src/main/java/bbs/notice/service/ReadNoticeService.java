package bbs.notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.notice.dao.NoticeContentDao;
import bbs.notice.dao.NoticeDao;
import bbs.notice.model.Notice;
import bbs.notice.model.NoticeContent;

public class ReadNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public NoticeData getNotice(int noticeNno, boolean increaseReadCount) throws NoticeNotFoundException {
	try(Connection conn = ConnectionProvider.getConnection()){	
		Notice notice = NoticeDao.selectById(conn, noticeNno);
		if(notice == null) {
			throw new NoticeNotFoundException();
		}
		NoticeContent content = contentDao.selectById(conn, noticeNno);
		if(content == null) {
			throw new NoticeContentNotFoundException();
		}
		if(increaseReadCount) {
			noticeDao.increaseReadCount(conn, noticeNno);
		}
		return new NoticeData(notice, content);
	}catch(SQLException e) {
		throw new RuntimeException(e);
	}
}
	
}
