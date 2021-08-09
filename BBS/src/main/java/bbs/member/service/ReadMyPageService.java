package bbs.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class ReadMyPageService {
	// 20210802
	private MemberDao memberDao = new MemberDao();

	public Member getMember(String id) { // ���̵�� ��� ���� ���
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectByIdPlusImg(conn, id); // �Է� id ���� ���� ��������

			if (member == null) { // ��� ���� ���
				throw new MemberNotFoundException();
			}

			return member; // �����ڷ� �ȿ� �ִ� ������ ������

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
