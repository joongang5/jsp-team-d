package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.review.dao.ReviewContentDao;
import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import bbs.review.model.ReviewContent;

public class WriteReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	private ReviewContentDao contentDao = new ReviewContentDao();

	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Review review = toReview(req);
			Review savedReview = reviewDao.insert(conn, review);
			if(savedReview == null) {
				throw new RuntimeException("fail to insert Review");
			}
			ReviewContent content = new ReviewContent(
					savedReview.getNumber(),
					req.getContent());
			ReviewContent savedContent = contentDao.insert(conn, content);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert review_content");
				
			}
			conn.commit();
			
			
			return savedReview.getNumber();
			
			
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
	private Review toReview(WriteRequest req) {
		Date now = new Date();
		return new Review(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
