package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.command.PermissionDeniedException;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;

public class ModifyOffMeetService {

	private OffMeetDao offmeetDao = new OffMeetDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			OffMeet offmeet = offmeetDao.selectById(conn, modReq.getOffmeetNumber());
			if(offmeet == null) {
				throw new OffMeetNotFoundException();
				}
			if(!canModify(modReq.getUserId(), offmeet)) {
				throw new PermissionDeniedException();
			}
			offmeetDao.update(conn, modReq.getOffmeetNumber(), modReq.getTitle(), modReq.getContent());
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(String modfyingUserId, OffMeet offmeet) {
		return offmeet.getWriter().getId().equals(modfyingUserId);
	}
}
