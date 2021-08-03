package bbs.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bbs.jdbc.JdbcUtil;
import bbs.member.model.Member;

public class MemberDao {
	
	public Member selectById(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member(
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("birth_date"),
						toDate(rs.getTimestamp("reg_date")));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
	
	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO member (id, name, password, email, birth_date, reg_date) VALUES (?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setString(4, mem.getEmail());
			pstmt.setString(5, mem.getBirthDate());
			pstmt.setTimestamp(6, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}
	
	public void update(Connection conn, Member member) throws SQLException {
		String sql = "UPDATE member SET name=?, password=?, email=? where id=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		}
	}
}
