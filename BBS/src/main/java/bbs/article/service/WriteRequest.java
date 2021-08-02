package bbs.article.service;

import java.util.Map;

import bbs.article.model.Writer;
import bbs.util.ErrorUtil;

public class WriteRequest {

	private Writer writer;
	private String title;
	private String content;

	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		ErrorUtil.checkEmpty(errors, "title", title);
	}
}
