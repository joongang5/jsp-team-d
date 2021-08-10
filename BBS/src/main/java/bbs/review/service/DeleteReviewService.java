package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.review.dao.ReviewDao;

public class DeleteReviewService { //20210809 이현아
	
	public void deleteReview(int reviewNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			ReviewDao reviewDao = new ReviewDao();
			
			reviewDao.DeleteReview(conn, reviewNo);
			System.out.println("삭제 성공");
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	}

