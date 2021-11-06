package bbs.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.command.PermissionDeniedException;
import bbs.article.dao.ArticleContentDao;
import bbs.article.dao.ArticleDao;
import bbs.article.model.Article;
import bbs.util.PermissionChecker;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class ModifyArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao articleContentDao = new ArticleContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, modReq.getArticleNo());
			if (article == null) {
				throw new ArticleNotFoundException();
			}

			if (PermissionChecker.canModify(modReq.getUserId(), article) == false) {
				throw new PermissionDeniedException();
			}
			
			articleDao.update(conn, modReq.getArticleNo(), modReq.getTitle());
			articleContentDao.update(conn, modReq.getArticleNo(), modReq.getContent());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException();
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
