package bbs.notice.service;

import java.util.Map;

public class ModifyRequest {
	private String userId;
	private int noticeNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String userId, int noticeNumber, String title, String content) {
		this.userId = userId;
		this.noticeNumber = noticeNumber;
		this.title = title;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public int getNoticeNumber() {
		return noticeNumber;
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
