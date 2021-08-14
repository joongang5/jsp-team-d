package bbs.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbs.jdbc.JdbcUtil;

public abstract class BasePagingDao<T> {

	private String tableName;
	private String orderRule;
	
	public BasePagingDao(String tableName, String orderRule) {
		this.tableName = tableName;
		this.orderRule = orderRule;
	}
	
	public int selectCount(Connection conn, String condition) throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) FROM ");
			sb.append(tableName);
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
	
	public List<T> select(Connection conn, int startRow, int size, String condition) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM ");
			sb.append(tableName);
			if (condition != null) {
				sb.append(" WHERE ");
				sb.append(condition);
			}
			
			if (orderRule != null && orderRule.isEmpty() == false) {
				sb.append(" ORDER BY ");
				sb.append(orderRule);
			}
			sb.append(" limit ?,?");
			
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

	protected abstract T convert(ResultSet rs) throws SQLException;
}
