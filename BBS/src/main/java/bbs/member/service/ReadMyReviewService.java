package bbs.member.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.article.model.Article;
import bbs.jdbc.ConnectionProvider;
import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import bbs.review.service.ReviewNotFoundException;

public class ReadMyReviewService {
	
	private ReviewDao	reviewDao = new ReviewDao();
	
	List<Review> review = new ArrayList<Review>();
	
	public List<Review> getMyReview(String id) { // ���̵�� ��� ���� ���
		try (Connection conn = ConnectionProvider.getConnection()) {
			List<Review> review = reviewDao.selectByIdReall(conn, id); // �Է� id ���� ���� ��������
			

			if (review == null) { // ��� ���� ���
				throw new ReviewNotFoundException();
			}
			
			return review; // �����ڷ� �ȿ� �ִ� ������ ������

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		}
}
