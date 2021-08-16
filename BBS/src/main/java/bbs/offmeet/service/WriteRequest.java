package bbs.offmeet.service;

import java.util.Map;

import bbs.offmeet.model.Writer;

public class WriteRequest {
	private Writer writer;
	private String title;
	private String content;					
	private String juso;				
	private String sangho;				
	private String tel;				
	
	public WriteRequest(Writer writer, String title, String content, String juso, String sangho, String tel) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.juso = juso;
		this.sangho = sangho;
		this.tel = tel;
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
	public String getJuso() {
		return juso;
	}
	public String getSangho() {
		return sangho;
	}
	public String getTel() {
		return tel;
	}
	//데이터 유효 검사 없다면 에러 발생
	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
