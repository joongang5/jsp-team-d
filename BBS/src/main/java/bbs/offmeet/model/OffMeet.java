package bbs.offmeet.model;

import java.util.Date;

public class OffMeet {

	private Integer number;
	private String content;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modifiedDate;
	private int readCount;
	private int offmeetNo;
	private String offmeetContent;
	private String writerId;
	private String writerName;
	private String juso;
	private String sangho;
	private String tel;

	public OffMeet(int offmeet_no, String offmeet_content, String writer_id, String writer_name, String title,
			Date regDate, Date modifiedDate, int readCount) {
		this.offmeetNo = offmeet_no;
		this.offmeetContent = offmeet_content;
		this.writerId = writer_id;
		this.writerName = writer_name;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}

	public int getOffmeetNo() {
		return offmeetNo;
	}

	public void setOffmeetNo(int offmeetNo) {
		this.offmeetNo = offmeetNo;
	}

	public String getOffmeetContent() {
		return offmeetContent;
	}

	public void setOffmeetContent(String offmeetContent) {
		this.offmeetContent = offmeetContent;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public OffMeet(Integer number, String content, Writer writer, String title, Date regDate, Date modifiedDate,
			String juso, String sangho, String tel, int readCount) {
		this.number = number;
		this.content = content;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.juso = juso;
		this.sangho = sangho;
		this.tel = tel;
		this.readCount = readCount;
		
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getJuso() {
		return juso;
	}

	public void setJuso(String juso) {
		this.juso = juso;
	}
	public String getSangho() {
		return sangho;
	}

	public void setSangho(String sangho) {
		this.sangho = sangho;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}