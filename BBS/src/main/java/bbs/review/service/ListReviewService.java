package bbs.review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.review.dao.ReviewDao;
import bbs.review.model.Review;
import bbs.jdbc.ConnectionProvider;

public class ListReviewService {

	private ReviewDao reviewDao = new ReviewDao();
	private int size = 10;

	public ReviewPage getReviewPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			//전체 게시글의 개수를 구한다.
			int total = reviewDao.selectCount(conn);
			//pageNum에 해당하는 게시글 목록을 구한다.
			List<Review> content = reviewDao.select(conn, (pageNum - 1) * size, size);
			//review 객체를 리턴한다.
			return new ReviewPage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
