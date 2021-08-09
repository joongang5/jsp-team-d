package bbs.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
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
						toDate(rs.getTimestamp("reg_date"))
						
						);
					
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
	
	public Member selectByIdPlusImg(Connection conn, String id) {
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
						toDate(rs.getTimestamp("reg_date")),
						rs.getString("img")
						);
					
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
	
	
	
	
	
	
	
	public Member whereMyImg(Connection conn,String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT id,img FROM member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member(
						rs.getString("id"),
						rs.getString("img"));
					
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
	
	
	public Member selectByEmail(Connection conn, String email) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM member where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member(
						rs.getString("id"),
						rs.getString("name"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("birth_date"),
						toDate(rs.getTimestamp("reg_date"))
					
						);
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
	
	public void updateProfile(Connection conn, Member member) throws SQLException{
		String sql = "UPDATE member SET img=? where id=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getImgName());
			pstmt.setString(2, member.getId());
			pstmt.executeUpdate();
		}
	}


	public int idCheck(String id) {
		int result = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE id=?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return result;
	}
	
	public int nameCheck(String name) {
		int result = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE name=?";
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return result;
	}
	
	public int emailCheck(String email) {
		int result = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM member WHERE email=?";

		try {
			Connection conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return result;
	}
	
	

	

	
}
