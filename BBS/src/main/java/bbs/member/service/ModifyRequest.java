package bbs.member.service; 

public class ModifyRequest {  //20210803
	
	private String password;
	private String email;

	
	
	public ModifyRequest(String id) {
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
