package bbs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.notice.model.NoticeContent;

public class NoticeContentDao {

	public NoticeContent insert(Connection conn, NoticeContent content) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO notice_content (notice_no, content) VALUES (?,?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0) {
				return content;
			} else {
				return null;				
			}
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public NoticeContent selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM notice_content WHERE notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			NoticeContent content = null;
			if(rs.next()) {
				content = new NoticeContent(rs.getInt("notice_no"), rs.getString("content"));
			}
			return content;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
}