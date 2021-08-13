package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class MyPointService { // 20210809 ��

	// 2. ����Ʈ�� �߰���Ų��
	public void upgradeMyPoint(String id) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			MemberDao memberDao = new MemberDao();
			// 2.1 �۾� ����� member ������ DB���� �о �´�
			Member member = memberDao.selectByIdPlusImg(conn, id); 
			
			if (member == null) {
				throw new MemberNotFoundException();
			}

			// 2.2 �о�� ���� ����Ʈ ���� maxPoint�� �Ѿ�� ������
			MemberGradeUpRequest request = createLevelUpRequest(member);
			request.gradeUp();
			
			memberDao.updatePointAndLevel(conn, request);
			System.out.println("5 ����Ʈ ȹ��");
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
