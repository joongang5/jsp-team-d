package bbs.notice.service;

import bbs.notice.model.Notice;
import bbs.notice.model.NoticeContent;

public class NoticeData {
	private Notice notice;
	private NoticeContent content;
	
	public NoticeData(Notice notice, NoticeContent content) {
		this.notice = notice;
		this.content = content;
	}

	public Notice getNotice() {
		return notice;
	}

	public String getContent() {
		return content.getContent();
	}
	
	
	
}
