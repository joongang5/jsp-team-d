package bbs.member.service;

public class YesOrNoRequest { 
	 private String yes;
	 private String no;
	private String adminKey;
	private String userKey;
	 
	public YesOrNoRequest(String answer){
		if(answer == "yes") {
			this.setYes(answer);
			this.getYes();
		}else {
			this.setNo(answer);
			this.getNo();
		}
	}
	
	public YesOrNoRequest() {
		
	}
	public String getYes() {
		return yes;
	}
	public void setYes(String yes) {
		this.yes = yes;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	public String getAdminKey() {
		return adminKey;
	}

	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	 
}
