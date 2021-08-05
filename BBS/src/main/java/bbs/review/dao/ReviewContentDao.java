package bbs.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.review.model.ReviewContent;

public class ReviewContentDao {

	public ReviewContent insert(Connection conn, ReviewContent content) throws SQLException {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO review_content" + "(review_no, content) VALUES (?,?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int insertedCount = pstmt.executeUpdate();
			if (insertedCount > 0) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public ReviewContent selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = conn.prepareStatement("SELECT * FROM review_content WHERE review_no = ?");

			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ReviewContent content = null;
			if (rs.next()) {
				content = new ReviewContent(rs.getInt("review_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public int update(Connection conn, int no, String content) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update review_content set content = ? "
						+ "where review_no = ?")) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
}