package bbs.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import bbs.article.dao.ArticleContentDao;
import bbs.article.dao.ArticleDao;
import bbs.article.model.Article;
import bbs.article.model.ArticleContent;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;

public class WriteArticleService {

	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDao articleDao = new ArticleDao(); 
			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(conn, article);
			if (savedArticle == null) {
				throw new RuntimeException("fail to insert article");
			}
			
			ArticleContentDao contentDao = new ArticleContentDao();
			ArticleContent content = new ArticleContent(savedArticle.getNo(), req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);
			if (savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			return savedArticle.getNo();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Article toArticle(WriteRequest req) {
		Date now = new Date();
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
