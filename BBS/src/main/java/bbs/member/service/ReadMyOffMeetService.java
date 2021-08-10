package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.service.OffMeetNotFoundException;

public class ReadMyOffMeetService {
	private OffMeetDao offMeetDao = new OffMeetDao();

	public OffMeet readMyOffMeet(String id) { // ���̵�� ��� ���� ���
		try (Connection conn = ConnectionProvider.getConnection()) {
			OffMeet offMeet = offMeetDao.selectById(conn, id); // �Է� id ���� ���� ��������

			if(offMeet == null) {
				throw new OffMeetNotFoundException();
			}

			return offMeet; // �����ڷ� �ȿ� �ִ� ������ ������

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
}
