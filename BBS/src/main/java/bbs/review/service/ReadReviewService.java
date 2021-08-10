package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.review.dao.ReviewContentDao;
import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import bbs.review.model.ReviewContent;

public class ReadReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	private ReviewContentDao contentDao = new ReviewContentDao();

	public ReviewData getReview(int reviewNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			Review review = reviewDao.selectById(conn, reviewNum);
			if (review == null) {
				throw new ReviewNotFoundException();
			}
			ReviewContent content = contentDao.selectById(conn, reviewNum);
			if (content == null) {
				throw new ReviewContentNotFoundException();
			}
			if (increaseReadCount) {
				reviewDao.increaseReadCount(conn, reviewNum);
			}
			return new ReviewData(review, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}
}
