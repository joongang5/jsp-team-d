package bbs.offmeet.model;

public class OffMeetContent {
	
	private Integer number;
	private String content;

	public OffMeetContent(Integer number, String content) {
		this.number = number;
		this.content = content;
	}

	public Integer getArticleNo() {
		return number;
	}

	public String getContent() {
		return content;
	}

	public long getNumber() {
		return 0;
	}

}

