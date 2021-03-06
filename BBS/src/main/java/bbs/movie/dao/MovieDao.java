package bbs.movie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bbs.logic.dao.BasePagingDao;
import bbs.movie.model.BaseMovie;
import bbs.movie.model.Movie;
import dev.jdbc.JdbcUtil;

public class MovieDao<T extends Movie> extends BasePagingDao<T> {

	public MovieDao() {
		super("movie", null);
	}
	
	public MovieDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}
	
	public void insert(Connection conn, Movie movie) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO movie VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
			insertPstmt.setString(10, movie.getDirectors());
			insertPstmt.setString(11, movie.getCompanyCd());
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
	
	@SuppressWarnings("unchecked")
	protected T convert(ResultSet rs) throws SQLException {
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
				rs.getString("directors"),
				rs.getString("company_cd"));
		return (T)movie;
	}

	public Movie selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM movie WHERE movie_cd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Movie movie = null;
			if (rs.next()) {
				movie = convert(rs);
			}
			return movie;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public ArrayList<BaseMovie> selectAllToBase(Connection conn) throws SQLException {
		ArrayList<BaseMovie> list = new ArrayList<BaseMovie>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM movie";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(convert(rs));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return list;
	}
}
