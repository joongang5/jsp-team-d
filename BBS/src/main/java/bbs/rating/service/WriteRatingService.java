package bbs.rating.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.dao.ArticleContentDao;
import bbs.article.dao.ArticleDao;
import bbs.article.model.Article;
import bbs.article.model.ArticleContent;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.rating.dao.RatingDao;
import bbs.rating.model.Rating;

public class WriteRatingService {
	
	private RatingDao dao = new RatingDao();
	
	public void write(Rating rating) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Rating savedRating = dao.insert(conn, rating);
			if (savedRating == null) {
				throw new RuntimeException("fail to insert rating");
			}
			
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
