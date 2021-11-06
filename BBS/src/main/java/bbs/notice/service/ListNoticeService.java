package bbs.notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.notice.dao.NoticeDao;
import bbs.notice.model.Notice;
import dev.jdbc.ConnectionProvider;

public class ListNoticeService {

	private NoticeDao noticeDao = new NoticeDao();
	private int size = 10;

	public NoticePage getNoticePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = noticeDao.selectCount(conn);
			List<Notice> content = noticeDao.select(conn, (pageNum - 1) * size, size);
			return new NoticePage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}