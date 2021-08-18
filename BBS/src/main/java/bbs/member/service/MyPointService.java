package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class MyPointService { // 20210809 현

	// 2. 포인트를 추가시킨다
	public void upgradeMyPoint(String id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			// 2.1 글쓴 사람의 member 정보를 DB에서 읽어만 온다
			Member member = memberDao.selectByIdPlusImg(conn, id); 
			
			if (member == null) {
				throw new MemberNotFoundException();
			}

			// 2.2 읽어온 현재 포인트 값이 maxPoint를 넘어서면 레벨업
			MemberGradeUpRequest request = createLevelUpRequest(member);
			request.gradeUp();
			
			memberDao.updatePointAndLevel(conn, request);
			System.out.println("5 포인트 획득");
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private MemberGradeUpRequest createLevelUpRequest(Member member) {
		return new MemberGradeUpRequest(member.getId(), member.getGrade(), member.getMyPoint());
	}
}

