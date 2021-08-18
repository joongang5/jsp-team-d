package bbs.offmeet.dao;

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
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.Writer;

public class OffMeetDao {
	public OffMeet insert(Connection conn, OffMeet offmeet) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String sql = "insert into offmeet (offmeet_content, title, regdate, moddate, sangho, juso, tel, read_cnt, member_no) values (?,?,?,?,?,?,?,0,(SELECT m_no FROM member WHERE id=?))";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, offmeet.getContent());
			pstmt.setString(2, offmeet.getTitle());
			pstmt.setTimestamp(3, toTimestamp(offmeet.getRegDate()));
			pstmt.setTimestamp(4, toTimestamp(offmeet.getModifiedDate()));
			pstmt.setString(5, offmeet.getJuso());
			pstmt.setString(6, offmeet.getSangho());
			pstmt.setString(7, offmeet.getTel());
			pstmt.setString(8, offmeet.getWriter().getId());
			int insertedCount = pstmt.executeUpdate();
			System.out.println(insertedCount);

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from offmeet");

				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new OffMeet(newNum, offmeet.getContent(), offmeet.getWriter(), offmeet.getTitle(),
							offmeet.getRegDate(), offmeet.getModifiedDate(), offmeet.getJuso(),offmeet.getSangho(), offmeet.getTel(), 0);
				}
			}

			return null;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from offmeet");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}

	}

	public List<OffMeet> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from offmeet_view limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<OffMeet> result = new ArrayList<>();
			while (rs.next()) {
				result.add(converOffmeet(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}
	}

	private OffMeet converOffmeet(ResultSet rs) throws SQLException {
		return new OffMeet(rs.getInt("offmeet_no"),
				rs.getString("offmeet_content"),
				new Writer(rs.getString("writer_id"), 
				rs.getString("writer_name")), 
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")), 
				toDate(rs.getTimestamp("moddate")), 
				rs.getString("juso"),
				rs.getString("sangho"),
				rs.getString("tel"),
				rs.getInt("read_cnt"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public OffMeet selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from offmeet_view where offmeet_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			OffMeet offmeet = null;
			if (rs.next()) {
				offmeet = converOffmeet(rs);
			}
			return offmeet;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update offmeet set read_cnt = read_cnt + 1 where offmeet_no = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int update(Connection conn, int no, String title, String content) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update offmeet set title = ?, offmeet_content = ?," + "moddate = NOW()" + "where offmeet_no = ?")) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			return pstmt.executeUpdate();
		}
	}

	public static int delete(Connection conn, int no, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;

		try {
			String sql = "DELETE FROM offmeet WHERE offmeet_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			// pstmt.setString(2, id);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public OffMeet selectById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM offmeet_view where writer_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				OffMeet offMeet = new OffMeet(rs.getInt("offmeet_no"),
						rs.getString("offmeet_content"),
						rs.getString("writer_id"), 
						rs.getString("writer_name"), 
						rs.getString("title"),
						toDate(rs.getTimestamp("regdate")), 
						toDate(rs.getTimestamp("moddate")), 
						rs.getInt("read_cnt"));

				return offMeet;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}

//	public kakao kakaoin(Connection conn, kakao kakao) throws SQLException {
//		PreparedStatement pstmt = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("insert into kakao_data (juso, sangho, TEL) values (?,?,?)");
//			pstmt.setString(1, kakao.getJuso());
//			pstmt.setString(2, kakao.getSangho());
//			pstmt.setInt(3, kakao.getTEL());
//			return null;
//
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(stmt);
//			JdbcUtil.close(pstmt);
//		}
//
//
//	}

}
