package bbs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bbs.jdbc.JdbcUtil;
import bbs.notice.model.Notice;
import bbs.notice.model.Writer;

public class NoticeDao {
	
	public Notice insert(Connection conn, Notice notice) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO notice (writer_id, writer_name, title, regdate, moddate, read_cnt) values(?,?,?,?,?,0)");
			
			pstmt.setString(1, notice.getWriter().getId());
			pstmt.setString(2, notice.getWriter().getName());
			pstmt.setString(3, notice.getTitle());
			pstmt.setTimestamp(4, toTimeStamp(notice.getRegDate()));
			pstmt.setTimestamp(5, toTimeStamp(notice.getModifiedDate()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0 ) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT last_insert_id() from notice");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Notice(newNum, notice.getWriter(), notice.getTitle(), notice.getRegDate(),
							notice.getModifiedDate(), 0);
				}
			}
			return null;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
			
		}
		
				
	}

	private Timestamp toTimeStamp(Date date) {
		
		return new Timestamp(date.getTime());
	}

	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM notice");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Notice> select(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM notice " + "ORDER BY notice_no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Notice> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertNotice(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Notice convertNotice(ResultSet rs) throws SQLException{
		return new Notice(rs.getInt("notice_no"), 
				new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), toDate(rs.getTimestamp("regdate")), 
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
	}

	private Date toDate(Timestamp timestamp) {
		
		return new Date(timestamp.getTime());
	}

	public Notice selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM notice WHERE notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Notice notice = null;
			if (rs.next()) {
				notice = convertNotice(rs);
			}
			return notice;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseReadCount(Connection conn, int no) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE notice set read_cnt = read_cnt + 1 " + "WHERE notice_no=?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int update (Connection conn, int no, String title)throws SQLException{
		try(PreparedStatement pstmt =
				conn.prepareStatement(
						"UPDATE notice SET title = ?, moddate = now() "+
						"WHERE notice_no=?")){
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		try {
			String sql = "DELETE FROM notice WHERE notice_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			System.out.println(result + "    !!!!!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
