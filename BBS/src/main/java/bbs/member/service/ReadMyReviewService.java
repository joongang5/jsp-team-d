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
	
	public List<Review> getMyReview(String id) { // 아이디로 멤버 정보 얻기
		try (Connection conn = ConnectionProvider.getConnection()) {
			List<Review> review = reviewDao.selectByIdReall(conn, id); // 입력 id 관련 정보 가져오기
			

			if (review == null) { // 멤버 없을 경우
				throw new ReviewNotFoundException();
			}
			
			return review; // 생성자로 안에 있는 데이터 꺼내기

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		}
}
