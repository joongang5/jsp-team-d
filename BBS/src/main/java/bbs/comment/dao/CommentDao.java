package bbs.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.comment.model.Comment;
import dev.jdbc.JdbcUtil;

public class CommentDao {

	private String tableName;
	
	public CommentDao(String tableName) {
		this.tableName = tableName;
	}
	
	public void insert(Connection conn, Comment comment) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = String.format("INSERT INTO %s (article_no, content, member_no) VALUES (?,?,(SELECT m_no FROM member WHERE id=?))", tableName);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getArticleNo());
			pstmt.setString(2, comment.getContent());
			pstmt.setString(3, comment.getMemberId());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	

	public Comment selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = String.format("SELECT * FROM %s where no=?", tableName);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Comment comment = null;
			if (rs.next()) {
				comment = convert(rs);
			}
			return comment;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, int no, String content) throws SQLException {
		String sql = String.format("UPDATE %s set content=? WHERE no=?", tableName);
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

	public void delete(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			String sql = String.format("DELETE FROM %s WHERE no=?", tableName);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Comment convert(ResultSet rs) throws SQLException {
		Comment comment = new Comment(
				rs.getInt("no"),
				rs.getInt("article_no"),
				null,
				rs.getString("content"),
				rs.getDate("reg_date"));
		
		return comment;
	}
}
