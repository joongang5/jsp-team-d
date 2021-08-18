package bbs.comment.model;

import java.util.Date;

public class Comment {
	
	private int no;
	private int articleNo;
	private String memberId;
	private String content;
	private Date regDate;
	
	public Comment(int articleNo, String memberId, String content) {
		this.no = 0;
		this.articleNo = articleNo;
		this.memberId = memberId;
		this.content = content;
		this.regDate = null;
	}
	
	public Comment(int no, int articleNo, String memberId, String content, Date regDate) {
		this.no = no;
		this.articleNo = articleNo;
		this.memberId = memberId;
		this.content = content;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getContent() {
		return content;
	}

	public Date getRegDate() {
		return regDate;
	}
}
