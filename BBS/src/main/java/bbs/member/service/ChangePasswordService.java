package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class ChangePasswordService {

	public void changePassword(String userId, String curPw, String newPw) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectById(conn, userId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			if (member.matchPassword(curPw) == false) {
				throw new InvalidPasswordException();
			}
			
			member.changePassword(newPw);
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	
}
