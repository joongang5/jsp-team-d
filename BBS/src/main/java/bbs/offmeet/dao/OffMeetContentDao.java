package bbs.offmeet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.jdbc.JdbcUtil;
import bbs.offmeet.model.OffMeetContent;

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
}
