package bbs.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.auth.model.User;
import bbs.jdbc.ConnectionProvider;
import bbs.member.dao.MemberDao;
import bbs.member.model.Member;

public class LoginService {
	
	public User login(String id, String password) {
		MemberDao memberDao = new MemberDao();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, id);
			if (member == null) {
				throw new LoginFailException();
			}
			if (member.matchPassword(password) == false) {
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
