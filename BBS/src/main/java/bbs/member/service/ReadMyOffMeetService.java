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

	public OffMeet readMyOffMeet(String id) { // 아이디로 멤버 정보 얻기
		try (Connection conn = ConnectionProvider.getConnection()) {
			OffMeet offMeet = offMeetDao.selectById(conn, id); // 입력 id 관련 정보 가져오기

			if(offMeet == null) {
				throw new OffMeetNotFoundException();
			}

			return offMeet; // 생성자로 안에 있는 데이터 꺼내기

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
}
