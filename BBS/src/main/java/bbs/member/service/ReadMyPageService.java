package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class ReadMyPageService {
	// 20210802
	private MemberDao memberDao = new MemberDao();

	public Member getMember(String id) { // 아이디로 멤버 정보 얻기
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectByIdPlusImg(conn, id); // 입력 id 관련 정보 가져오기

			if (member == null) { // 멤버 없을 경우
				throw new MemberNotFoundException();
			}

			return member; // 생성자로 안에 있는 데이터 꺼내기

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
