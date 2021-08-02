package bbs.article.model;

public class ArticleContent {

	private Integer articleNo;
	private String content;

	public ArticleContent(Integer articleNo, String content) {
		this.articleNo = articleNo;
		this.content = content;
	}

	public Integer getArticleNo() {
		return articleNo;
	}

	public String getContent() {
		return content;
	}
}
