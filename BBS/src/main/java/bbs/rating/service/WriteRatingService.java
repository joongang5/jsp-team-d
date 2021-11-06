package bbs.rating.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.rating.dao.RatingDao;
import bbs.rating.model.Rating;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class WriteRatingService {
	
	private RatingDao<Rating> dao = new RatingDao<Rating>();
	
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
