package bbs.auth.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;


//싱글톤 패턴
//Dao
public class UsersRepository {

	private static final String TAG = "UserRepository : ";
	private static UsersRepository instance = new UsersRepository();

	private UsersRepository() {
	}

	public static UsersRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Users findByUsername(String username) {
		final String SQL = "select * from users where username=?";
		Users user = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("Address"));
				user.setUserProfile(rs.getString("userprofile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createData"));

			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsername : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return null;
	}

	public Users findByUsernameAndPassword(String username, String password) {
		final String SQL = "select id, username, email, address, userprofile, userrole, createDate from users where username=? and password=?";
		Users user = null;
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			// if -> rs
			if (rs.next()) {
				user = new Users();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setUserRole(rs.getString("userRole"));
				user.setCreateDate(rs.getTimestamp("createDate"));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findByUsernameAndPassword : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return null;
	}

	public int save(Users user) {
		final String SQL = "insert into users (id,username,password,email,address,userrole,createdate) values(USER_SEQ.nextval,?,?,?,?,?,sysdate) ";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUserRole());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	public int update(int id, String UserProfile) {
		final String SQL = "update users set UserProfile = ? where id=?";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, UserProfile);
			pstmt.setInt(2, id);

			System.out.println(pstmt.executeUpdate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	public int update(Users user) {
		final String SQL = "update users set password=?, email=? , address=? where id=?";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setInt(4, user.getId());

			System.out.println(pstmt.executeUpdate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	public int deleteByID(int id) {
		final String SQL = "";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteByID : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	public List<Users> findAll() {
		final String SQL = "";
		List<Users> users = new ArrayList<>();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}

		return null;
	}

	public Users findById(int id) {
		final String SQL = "select * from users where id = ?";
		Users user = new Users();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, id);
			// 물음표 완성하기
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = Users.builder().id(rs.getInt("id")).username(rs.getString("username"))
						.email(rs.getString("email")).address(rs.getString("address"))
						.userProfile(rs.getString("userProfile")).createDate(rs.getTimestamp("createDate")).build();
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return null;
	}

}
