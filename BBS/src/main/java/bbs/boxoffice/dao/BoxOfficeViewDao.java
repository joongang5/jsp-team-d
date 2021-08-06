package bbs.boxoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.boxoffice.model.BoxOfficeView;
import bbs.jdbc.JdbcUtil;
import bbs.logic.dao.BasePagingDao;

public class BoxOfficeViewDao<T extends BoxOfficeView> implements BasePagingDao<T> {

	@Override
	public int selectCount(Connection conn, String condition) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) FROM box_office_view");
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
			sb.append("SELECT * FROM box_office_view");
			if (condition != null) {
				sb.append(" WHERE ");
				sb.append(condition);
			}
			sb.append(" ORDER BY rank ASC limit ?,?");
			
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

	private BoxOfficeView convert(ResultSet rs) throws SQLException {
		
		BoxOfficeView boxOfficeView = new BoxOfficeView(
				rs.getString("movie_nm"),
				rs.getInt("rank"),
				rs.getString("image"));
		
		return boxOfficeView;
	}
}
