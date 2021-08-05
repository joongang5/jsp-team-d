package bbs.notice.model;

public class NoticeContent {
	
	private Integer number;
	private String content;
	
	public NoticeContent(Integer number, String content) {
		this.number = number;
		this.content = content;
	}

	public Integer getNumber() {
		return number;
	}

	public String getContent() {
		return content;
	}
	
}
