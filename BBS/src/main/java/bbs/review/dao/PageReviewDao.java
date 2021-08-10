package bbs.review.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bbs.logic.dao.BasePagingDao;
import bbs.review.model.Review;

public class PageReviewDao<T extends Review> extends BasePagingDao<Review> {

	public PageReviewDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convert(ResultSet rs) throws SQLException {

		Review myReview = new Review(
				rs.getInt("review_no"),
				rs.getString("writer_id"),
				rs.getString("writer_name"),
				rs.getString("title"),
				rs.getString("content"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")));
		
		return (T)myReview;
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
