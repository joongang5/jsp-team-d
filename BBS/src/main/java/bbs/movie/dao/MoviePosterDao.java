package bbs.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.movie.model.MoviePoster;
import dev.jdbc.JdbcUtil;

public class MoviePosterDao {

	public MoviePoster selectByMovieCd(Connection conn, String movieCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM movie_poster WHERE movie_cd=?");
			pstmt.setString(1, movieCd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return new MoviePoster(
						rs.getString("movie_cd"),
						rs.getString("image"),
						rs.getFloat("user_rating"));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return null;
	}
	
	public int selectCountByMovieCd(Connection conn, String movieCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT count(*) FROM movie_poster WHERE movie_cd=?");
			pstmt.setString(1, movieCd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, MoviePoster moviePoster) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO movie_poster VALUES (?,?,?)";
			insertPstmt = conn.prepareStatement(sql);
			insertPstmt.setString(1, moviePoster.getMovieCd());
			insertPstmt.setString(2, moviePoster.getImage());
			insertPstmt.setFloat(3, moviePoster.getUserRating());
			insertPstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(resultPstmt);
			JdbcUtil.close(insertPstmt);
		}
	}
}
