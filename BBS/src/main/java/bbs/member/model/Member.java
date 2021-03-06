package bbs.member.model;

import java.util.Date;

public class Member {

	private String id;
	private String name;
	private String password;
	private String email;
	private String birth_date;
	private Date regDate;
	private String imgName;
	private String salt;
	private int grade;
	private int myPoint;

	public Member(String id, String name, String password, String email, String birth_date, Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.birth_date = birth_date;
		this.regDate = regDate;
		
	}

	public Member(String id, String name, String password, String email, String birth_date, Date regDate,String imgName, int grade, int myPoint ) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.birth_date = birth_date;
		this.regDate = regDate;
		this.imgName = imgName;
		this.grade = grade;
		this.myPoint = myPoint; 
	}
	
	public Member(String id, String imgName) {
		this.id = id;
		this.imgName = imgName;
	}
	
	public Member(String id, String name, String password, String email, String salt, String birth_date,
			Date regDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.salt = salt;
		this.birth_date = birth_date;
		this.regDate = regDate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getMyPoint() {
		return myPoint;
	}

	public void setMyPoint(int myPoint) {
		this.myPoint = myPoint;
	}
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birth_date;
	}

	public void setBirthDate(String birth_date) {
		this.birth_date = birth_date;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

	public void changePassword(String newPassword) {
		this.password = newPassword;
	}

	public void changeEmail(String newEmail) {
		this.email = newEmail;

	}


	public String getImgName() {
		return imgName;
	}


	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
