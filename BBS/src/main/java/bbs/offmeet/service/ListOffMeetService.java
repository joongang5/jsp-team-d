package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.jdbc.ConnectionProvider;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;

public class ListOffMeetService {
	
	private OffMeetDao offmeetDao = new OffMeetDao();
	private int size = 10;
	
	public OffMeetPage getOffMeetPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = offmeetDao.selectCount(conn);
			List<OffMeet> content = offmeetDao.select(conn, (pageNum -1) *size, size);
			return new OffMeetPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
