package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class SetNewPasswordService {

	public void setNewPassword(String userId, String newPw, String salt) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectByIdPlusSalt(conn, userId);
			if (member == null) {
				throw new MemberNotFoundException();
			}
	
			member.changePassword(newPw);
			member.setSalt(salt);
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
