package bbs.comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.service.ArticleNotFoundException;
import bbs.comment.dao.CommentDao;
import bbs.comment.model.Comment;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;

public class ModifyCommentService {
	
	private CommentDao dao = new CommentDao("comment_notice"); 

	public void modify(Comment comment) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment selectedComment = dao.selectById(conn, comment.getNo());
			if (selectedComment == null) {
				throw new ArticleNotFoundException();
			}
			
			dao.update(conn, comment.getNo(), comment.getContent());
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
