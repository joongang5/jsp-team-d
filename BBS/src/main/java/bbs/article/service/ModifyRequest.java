package bbs.article.service;

import java.util.Map;

import bbs.util.ErrorUtil;

public class ModifyRequest {

	private String userId;
	private int articleNo;
	private String title;
	private String content;

	public ModifyRequest(String userId, int articleNo, String title, String content) {
		this.userId = userId;
		this.articleNo = articleNo;
		this.title = title;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		ErrorUtil.checkEmpty(errors, title, "title");
	}
}
