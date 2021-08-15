package bbs.offmeet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bbs.logic.dao.BasePagingDao;
import bbs.offmeet.model.OffMeet;
import bbs.review.model.Review;

public class PageOffMeetDao<T extends OffMeet> extends BasePagingDao<OffMeet> {

	public PageOffMeetDao(String tableName, String orderRule) { // ������ ����,,
		super(tableName, orderRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convert(ResultSet rs) throws SQLException {

		OffMeet offMeet = new OffMeet(
				rs.getInt("offmeet_no"),
				rs.getString("offmeet_content"),
				rs.getString("writer_id"),
				rs.getString("writer_name"),
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
		
		return (T)offMeet;
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
