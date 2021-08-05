package bbs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.notice.model.NoticeContent;

public class NoticeContentDao {

	public NoticeContent insert(Connection conn, NoticeContent content) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO notice_content" + "(notice_no, content) VALUES (?,?)");
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

	public NoticeContent selectById(Connection conn, int noticeNno) {
		// TODO Auto-generated method stub
		return null;
	}
}