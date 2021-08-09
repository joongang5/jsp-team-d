package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class SaveImgDBService {
	public static Member modify(ModifyRequest modiReq) { //20210808 Çö
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberDao memberDao = new MemberDao();
			Member member = memberDao.whereMyImg(conn, modiReq.getId());
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			member.setImgName(modiReq.getImgName());
			memberDao.updateProfile(conn, member);
			conn.commit();
			return member;
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
