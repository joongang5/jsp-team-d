package bbs.notice.service;

import java.util.Map;

import bbs.notice.model.Writer;

public class WriteRequest {
	private Writer wirter;
	private String title;
	private String content;
	
	public WriteRequest(Writer writer, String title, String content) {
		this.wirter = writer;
		this.title = title;
		this.content = content;
	}
		
	
	public Writer getWirter() {
		return wirter;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
	
