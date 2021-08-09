package bbs.member.service; 

public class ModifyRequest {  //20210803
	

	private String email;
	private String id;
	private String imgName;
	
	public ModifyRequest(String id) {
		this.setId(id);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

}
