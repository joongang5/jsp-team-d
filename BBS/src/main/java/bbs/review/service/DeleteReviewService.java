package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.review.dao.ReviewDao;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class DeleteReviewService { //20210809 ������
	
	public void deleteReview(int reviewNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			
			ReviewDao reviewDao = new ReviewDao();
			
			reviewDao.DeleteReview(conn, reviewNo);
			System.out.println("���� ����");
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	}

