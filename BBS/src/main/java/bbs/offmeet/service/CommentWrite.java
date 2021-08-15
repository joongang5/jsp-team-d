package bbs.offmeet.service;

import java.util.Map;

import bbs.offmeet.model.Writer;

public class CommentWrite {
	private Writer writer;
	private String title;
	private String content;
	
	public CommentWrite(Writer writer, String title, String content) {
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
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
