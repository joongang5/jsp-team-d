package bbs.offmeet.service;

import java.util.Map;

import bbs.offmeet.model.Writer;

public class WriteRequest2 {
	private Writer writer;
	private String title;
	private String content;					
	private String mapResult;				
	
	public WriteRequest2(Writer writer, String title, String content, String mapResult) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.mapResult = mapResult;
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
	
	public String getMapResult() {
		return mapResult;
	}
	//데이터 유효 검사 없다면 에러 발생
	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
