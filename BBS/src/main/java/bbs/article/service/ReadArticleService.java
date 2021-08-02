package bbs.article.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.article.dao.ArticleContentDao;
import bbs.article.dao.ArticleDao;
import bbs.article.model.Article;
import bbs.article.model.ArticleContent;
import bbs.jdbc.ConnectionProvider;

public class ReadArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNo, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Article article = articleDao.selectById(conn, articleNo);
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			ArticleContent content = contentDao.selectById(conn, articleNo);
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}
			
			if (increaseReadCount) {
				articleDao.increaseReadCount(conn, articleNo);
			}
			return new ArticleData(article, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
