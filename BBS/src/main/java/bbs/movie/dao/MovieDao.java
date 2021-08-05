package bbs.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.jdbc.JdbcUtil;
import bbs.logic.dao.BasePagingDao;
import bbs.movie.model.Movie;

public class MovieDao<T extends Movie> implements BasePagingDao<T> {

	@Override
	public int selectCount(Connection conn, String condition) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) FROM movie");
			if (condition != null) {
				sb.append(" WHERE ");
				sb.append(condition);
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> select(Connection conn, int startRow, int size, String condition) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM movie");
			if (condition != null) {
				sb.append(" WHERE ");
				sb.append(condition);
			}
			sb.append(" ORDER BY open_dt DESC limit ?,?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<T> result = new ArrayList<T>();
			while (rs.next()) {
				result.add((T)convert(rs));
			}
			return result;	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Movie movie) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO movie VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			insertPstmt = conn.prepareStatement(sql);
			insertPstmt.setString(1, movie.getMovieCd());
			insertPstmt.setString(2, movie.getMovieNm());
			insertPstmt.setString(3, movie.getMovieNmEn());
			insertPstmt.setString(4, movie.getPrdtYear());
			insertPstmt.setString(5, movie.getOpenDt());
			insertPstmt.setString(6, movie.getTypeNm());
			insertPstmt.setString(7, movie.getPrdtStatNm());
			insertPstmt.setString(8, movie.getNationAlt());
			insertPstmt.setString(9, movie.getGenreAlt());
			insertPstmt.setString(10, movie.getRepNationNm());
			insertPstmt.setString(11, movie.getRepGenreNm());
			insertPstmt.setString(12, movie.getDirectors());
			insertPstmt.setString(13, movie.getPeopleNm());
			insertPstmt.setString(14, movie.getCompanys());
			insertPstmt.setString(15, movie.getCompanyCd());
			insertPstmt.setString(16, movie.getCompanyNm());
			insertPstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(resultPstmt);
			JdbcUtil.close(insertPstmt);
		}
	}
	
	public int selectCountByMovieCd(Connection conn, String movieCd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT count(*) FROM movie WHERE movie_cd=?");
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
	
	private Movie convert(ResultSet rs) throws SQLException {
		Movie movie = new Movie(
				rs.getString("movie_cd"),
				rs.getString("movie_nm"),
				rs.getString("movie_nm_en"),
				rs.getString("prdt_year"),
				rs.getString("open_dt"),
				rs.getString("type_nm"),
				rs.getString("prdt_stat_nm"),
				rs.getString("nation_alt"),
				rs.getString("genre_alt"),
				rs.getString("rep_nation_nm"),
				rs.getString("rep_genre_nm"),
				rs.getString("directors"),
				rs.getString("people_nm"),
				rs.getString("companys"),
				rs.getString("company_cd"),
				rs.getString("company_nm"));
		return movie;
	}
}
