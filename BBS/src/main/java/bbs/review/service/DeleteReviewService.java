package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.review.dao.ReviewDao;

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

