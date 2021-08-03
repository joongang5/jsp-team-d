package bbs.notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.jdbc.ConnectionProvider;
import bbs.notice.dao.NoticeDao;
import bbs.notice.model.Notice;

public class ListNoticeService {

	public List<Notice> getNotice() {
		NoticeDao dao = new NoticeDao();
		List<Notice> list = null;
		try {
			Connection conn = ConnectionProvider.getConnection();
			list = dao.selectList(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
