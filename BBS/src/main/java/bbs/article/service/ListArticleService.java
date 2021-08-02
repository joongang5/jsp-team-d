package bbs.article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.article.dao.ArticleDao;
import bbs.article.model.Article;
import bbs.jdbc.ConnectionProvider;

public class ListArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = articleDao.selectCount(conn);
			List<Article> content = articleDao.select(conn, (pageNum - 1) * size, size);
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
