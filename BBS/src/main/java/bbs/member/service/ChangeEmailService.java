package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class ChangeEmailService {

	public static void modify(ModifyRequest modiReq) { // ��
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectById(conn, modiReq.getId());
			if (member == null) {
				throw new MemberNotFoundException();
			}

			member.changeEmail(modiReq.getEmail());
			memberDao.update2(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

	}

}
