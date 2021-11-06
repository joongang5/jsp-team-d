package bbs.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.movie.model.MovieView;
import dev.jdbc.JdbcUtil;

public class MovieViewDao {

	public MovieView selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM movie_view WHERE movie_cd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			MovieView movie = null;
			if (rs.next()) {
				movie = convert(rs);
			}
			return movie;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private MovieView convert(ResultSet rs) throws SQLException {
		MovieView movie = new MovieView(
				rs.getString("movie_cd"),
				rs.getString("movie_nm"),
				rs.getString("movie_nm_en"),
				rs.getString("prdt_year"),
				rs.getString("open_dt"),
				rs.getString("type_nm"),
				rs.getString("prdt_stat_nm"),
				rs.getString("nation_alt"),
				rs.getString("genre_alt"),
				rs.getString("directors"),
				rs.getString("company_cd"),
				rs.getString("image"),
				rs.getFloat("user_rating"));
		return movie;
	}
}
