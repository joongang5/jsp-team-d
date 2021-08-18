package bbs.comment.model;

import java.util.Date;

public class CommentView {

	private int no;
	private int articleNo;
	private String content;
	private Date regDate;
	private String id;
	private String name;

	public CommentView(int no, int articleNo, String content, Date regDate, String id, String name) {
		this.no = no;
		this.articleNo = articleNo;
		this.content = content;
		this.regDate = regDate;
		this.id = id;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public String getContent() {
		return content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
