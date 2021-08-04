package bbs.notice.service;

import java.util.Map;

import bbs.notice.model.Writer;

public class WriteRequest {
	private Writer wirter;
	private String ntitle;
	private String ncontent;
	
	public WriteRequest(Writer writer, String ntitle, String ncontent) {
		this.wirter = writer;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
	}
	
	public Writer getWirter() {
		return wirter;
	}

	public String getNtitle() {
		return ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(ntitle == null || ntitle.trim().isEmpty()) {
			errors.put("ntitle", Boolean.TRUE);
		}
	}
}
	
