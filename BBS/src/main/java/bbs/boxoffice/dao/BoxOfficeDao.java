package bbs.boxoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.boxoffice.model.BoxOffice;
import bbs.jdbc.JdbcUtil;
import bbs.logic.dao.BasePagingDao;

public class BoxOfficeDao<T extends BoxOffice> extends BasePagingDao<T> {

	public BoxOfficeDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}

	public void insert(Connection conn, BoxOffice boxOffice) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO box_office "
					+ "(target_dt, rnum, rank, rank_inten, rank_old_and_new, movie_cd, movie_nm, open_dt, sales_amt, sales_share, sales_inten, sales_change, sales_acc, audi_cnt, audi_inten, audi_change, audi_acc, scrn_cnt, show_cnt) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			insertPstmt = conn.prepareStatement(sql);
			insertPstmt.setString(1, boxOffice.getTargetDt());
			insertPstmt.setInt(2, boxOffice.getRnum());
			insertPstmt.setInt(3, boxOffice.getRank());
			insertPstmt.setInt(4, boxOffice.getRankInten());
			insertPstmt.setString(5, boxOffice.getRankOldAndNew());
			insertPstmt.setString(6, boxOffice.getMovieCd());
			insertPstmt.setString(7, boxOffice.getMovieNm());
			insertPstmt.setString(8, boxOffice.getOpenDt());
			insertPstmt.setLong(9, boxOffice.getSalesAmt());
			insertPstmt.setFloat(10, boxOffice.getSalesShare());
			insertPstmt.setInt(11, boxOffice.getSalesInten());
			insertPstmt.setFloat(12, boxOffice.getSalesChange());
			insertPstmt.setFloat(13, boxOffice.getSalesAcc());
			insertPstmt.setInt(14, boxOffice.getAudiCnt());
			insertPstmt.setInt(15, boxOffice.getAudiInten());
			insertPstmt.setFloat(16, boxOffice.getAudiChange());
			insertPstmt.setInt(17, boxOffice.getAudiAcc());
			insertPstmt.setInt(18, boxOffice.getScrnCnt());
			insertPstmt.setInt(19, boxOffice.getShowCnt());
			insertPstmt.executeUpdate();
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(resultPstmt);
			JdbcUtil.close(insertPstmt);
		}
	}
	
	public int selectCountByTargetDt(Connection conn, String target_dt) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT count(*) FROM box_office_view WHERE target_dt=?");
			pstmt.setString(1, target_dt);
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
		
		BoxOffice boxOffice = new BoxOffice(
				rs.getString("target_dt"),
				rs.getInt("rnum"),
				rs.getInt("rank"),
				rs.getInt("rank_inten"),
				rs.getString("rank_old_and_new"),
				rs.getString("movie_cd"),
				rs.getString("movie_nm"),
				rs.getString("open_dt"),
				rs.getLong("sales_amt"),
				rs.getFloat("sales_share"),
				rs.getInt("sales_inten"),
				rs.getFloat("sales_change"),
				rs.getLong("sales_acc"),
				rs.getInt("audi_cnt"),
				rs.getInt("audi_inten"),
				rs.getFloat("audi_change"),
				rs.getInt("audi_acc"),
				rs.getInt("scrn_cnt"),
				rs.getInt("show_cnt"));
		
		return (T)boxOffice;
	}
}
