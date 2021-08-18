package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class JoinService {

	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.selectById(conn, joinReq.getId());
			if (member != null) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			
			memberDao.insert(conn, new Member(
							joinReq.getId(),
							joinReq.getName(),
							joinReq.getPassword(),
							joinReq.getEmail(),
							joinReq.getSalt(),
							joinReq.getBirthDate(),
							new Date()));
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
