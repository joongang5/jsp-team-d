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

public class OffMeetDao {
	public OffMeet insert(Connection conn, OffMeet offmeet) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into offmeet"
					+ "(writer_id, writer_name, title, regdate, moddate, read_cnt)"
					+ "(values(?,?,?,?,?,0)");
			pstmt.setString(1, offmeet.getWriter().getId());
			pstmt.setString(2, offmeet.getWriter().getId());
			pstmt.setString(3, offmeet.getWriter().getId());
			pstmt.setTimestamp(4, toTimestamp(offmeet.getRegDate()));
			pstmt.setTimestamp(5, toTimestamp(offmeet.getModifiedDate()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount >0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from offmeet");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new OffMeet(newNum,
							offmeet.getWriter(),
							offmeet.getTitle(),
							offmeet.getRegDate(),
							offmeet.getModifiedDate(),
							0);
				
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
