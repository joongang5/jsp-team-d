package bbs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.notice.model.NoticeContent;

public class NoticeContentDao {

	public NoticeContent selectById(Connection conn, int noticeNno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM noticeview WHERE nno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNno);
			rs = pstmt.executeQuery();
			NoticeContent content = null;
			if (rs.next()) {
				content = new NoticeContent();
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

}
}