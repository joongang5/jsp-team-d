package bbs.rating.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.rating.model.Rating;

public class RatingDao {

	public Rating insert(Connection conn, Rating rating) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO rating (userID, movieName, directorName, movieYear, "
					+ "agegroupDivide, genreDivide, ratingTitle, ratingContent, totalScore, "
					+ "immersionScore, visualbeautyScore, messageScore, likeCount) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			insertPstmt = conn.prepareStatement(sql);
			insertPstmt.setString(1, rating.getUserID());
			insertPstmt.setString(2, rating.getMovieName());
			insertPstmt.setString(3, rating.getDirectorName());
			insertPstmt.setInt(4, rating.getMovieYear());
			insertPstmt.setString(5, rating.getAgegroupDivide());
			insertPstmt.setString(6, rating.getGenreDivide());
			insertPstmt.setString(7, rating.getRatingTitle());
			insertPstmt.setString(8, rating.getRatingContent());
			insertPstmt.setString(9, rating.getTotalScore());
			insertPstmt.setString(10, rating.getImmersionScore());
			insertPstmt.setString(11, rating.getVisualbeautyScore());
			insertPstmt.setString(12, rating.getMessageScore());
			insertPstmt.setInt(13, rating.getLikeCount());
			int insertedCount = insertPstmt.executeUpdate();
			
			if (insertedCount > 0) {
				return rating;
			}
			return null;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(resultPstmt);
			JdbcUtil.close(insertPstmt);
		}
	}
}
