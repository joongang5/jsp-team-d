package bbs.article.model;

import java.util.Date;

public class Article {

	private Integer no;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private int readCnt;

	public Article(Integer no, Writer writer, String title, Date regDate, Date modifiedDate, int readCnt) {
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCnt = readCnt;
	}

	public void setNo(Integer no) {
		this.no = no;
	}
	
	public Integer getNo() {
		return no;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public int getReadCnt() {
		return readCnt;
	}
}
