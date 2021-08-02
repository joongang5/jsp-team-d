package bbs.article.service;

import bbs.article.model.Article;
import bbs.article.model.ArticleContent;

public class ArticleData {

	private Article article;
	private ArticleContent content;

	public ArticleData(Article article, ArticleContent content) {
		this.article = article;
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public ArticleContent getContent() {
		return content;
	}
}
