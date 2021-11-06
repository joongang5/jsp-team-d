package bbs.comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.comment.dao.CommentDao;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class DeleteCommentService {
	
	private CommentDao dao; 
	
	public DeleteCommentService(String tableName) {
		dao = new CommentDao(tableName);
	}
	
	public void delete(int no) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			dao.delete(conn, no);
		} catch (SQLException e){
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
