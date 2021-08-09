package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class MyPointService { // 20210809 ��

	public void upgradeMyPoint(String id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectByIdPlusImg(conn, id); 
			
			if (member == null) {
				throw new MemberNotFoundException();
			}

			memberDao.updatePointAndLevel(conn, member);
			System.out.println("5 ����Ʈ ȹ��");
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
