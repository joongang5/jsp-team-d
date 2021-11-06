package bbs.offmeet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.offmeet.model.OffMeetContent;
import dev.jdbc.JdbcUtil;

public class OffMeetContentDao {
	public OffMeetContent insert(Connection conn, OffMeetContent content) throws SQLException{
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into offmeet_content" +
					"(offmeet_no, content) values(?,?)");
					pstmt.setLong(1, content.getNumber());
					pstmt.setString(2, content.getContent());
					int insertedCount = pstmt.executeUpdate();
					if(insertedCount > 0) {
						return content;
					}else {
						return null;
						
					}
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		
	}
	
	public OffMeetContent selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from offmeet_content where offmeet_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			OffMeetContent content = null;
			if(rs.next()) {
				content = new OffMeetContent(rs.getInt("offmeet_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
