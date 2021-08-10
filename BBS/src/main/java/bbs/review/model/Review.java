package bbs.review.model;

import java.util.Date;
import java.util.List;

public class Review  {
	
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private int readCount;
	private String id;
	private String name;
	private String content;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Review(Integer number, Writer writer, String title, Date regDate, Date modifiedDate, int readCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}
	
	//이현아가 추가 
	public Review(Integer number,String id,String name, String title, String content, Date modifiedDate, Date regDate) {
		this.number = number;
		this.id = id;
		this.name = name;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.content = content;
	
	}
	

	public Integer getNumber() {
		return number;
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

	public int getReadCount() {
		return readCount;
	}

	
	
	
}
