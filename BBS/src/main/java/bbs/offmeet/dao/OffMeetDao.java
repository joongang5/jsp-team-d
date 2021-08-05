package bbs.offmeet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import bbs.jdbc.JdbcUtil;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.OffMeetContent;

public class OffMeetDao {
	public OffMeet insert(Connection conn, OffMeet offmeet, String content) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sql = "insert into offmeet (offmeet_content, writer_id, writer_name, title, regdate, moddate, read_cnt) values (?, ?,?,?,?,?,0)";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, offmeet.getWriter().getId());			
			pstmt.setString(3, offmeet.getWriter().getName());
			pstmt.setString(4, offmeet.getTitle());
			pstmt.setTimestamp(5, toTimestamp(offmeet.getRegDate()));
			pstmt.setTimestamp(6, toTimestamp(offmeet.getModifiedDate()));
			int insertedCount = pstmt.executeUpdate();
			System.out.println(insertedCount);
			
			if(insertedCount >0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from offmeet");
				
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new OffMeet(newNum,
							offmeet.getWriter(),
							offmeet.getTitle(), 
							offmeet.getRegDate(),
							offmeet.getModifiedDate(), 0);
					}
				}
			 
		return null;
		
		
	}finally{
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		JdbcUtil.close(pstmt);
	}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
