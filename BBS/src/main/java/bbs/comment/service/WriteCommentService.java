package bbs.comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.comment.dao.CommentDao;
import bbs.comment.model.Comment;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;

public class WriteCommentService {

	private CommentDao dao = new CommentDao("comment_notice");
	
	public void write(Comment comment) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			dao.insert(conn, comment);
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
