package bbs.rating.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	//검토 필요
	public List<Rating> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from rating order by ratingID desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			java.util.List<Rating> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertRating(rs));
			}
			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Rating convertRating(ResultSet rs) throws SQLException {
		return new Rating(rs.getInt("ratingID"),
						rs.getString("userID"),
						rs.getString("movieName"),
						rs.getString("directorName"),
						rs.getInt("movieYear"),
						rs.getString("agegroupDivide"),
						rs.getString("genreDivide"),
						rs.getString("ratingTitle"),
						rs.getString("ratingContent"),
						rs.getString("getTotalScore"),
						rs.getString("getImmersionScore"),
						rs.getString("visualbeautyScore"),
						rs.getString("messageScore"),
						rs.getInt("likeCount")
				);
	}
}
