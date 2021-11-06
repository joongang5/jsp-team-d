package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import dev.jdbc.ConnectionProvider;

public class ListReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	private int size = 10;

	public ReviewPage getReviewPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			//��ü �Խñ��� ������ ���Ѵ�.
			int total = reviewDao.selectCount(conn);
			//pageNum�� �ش��ϴ� �Խñ� ����� ���Ѵ�.
			List<Review> content = reviewDao.select(conn, (pageNum - 1) * size, size);
			//review ��ü�� �����Ѵ�.
			return new ReviewPage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
