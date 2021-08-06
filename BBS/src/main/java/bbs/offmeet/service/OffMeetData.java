package bbs.offmeet.service;

import bbs.offmeet.model.OffMeet;

public class OffMeetData {
	private OffMeet offmeet;
	private OffMeet content;
	
	public OffMeetData(OffMeet offmeet) {
		this.offmeet = offmeet;
	}
	public OffMeet getOffMeet() {
		return offmeet;
	}
	public String getContent() {
		return content.getContent();
	}
}
