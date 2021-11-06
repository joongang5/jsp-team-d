package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.review.dao.ReviewContentDao;
import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class ModifyReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	private ReviewContentDao contentDao = new ReviewContentDao();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Review review = reviewDao.selectById(conn, modReq.getReviewNumber());
			if (review == null) {
				throw new ReviewNotFoundException();

			}
			if (!canModify(modReq.getUserId(), review)) {
				throw new PermissionDeniedException();
			}
			reviewDao.update(conn, modReq.getReviewNumber(), modReq.getTitle());
			contentDao.update(conn, modReq.getReviewNumber(), modReq.getContent());
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modifyingUserId, Review review) {
		return review.getWriter().getId().equals(modifyingUserId);
	}
}
