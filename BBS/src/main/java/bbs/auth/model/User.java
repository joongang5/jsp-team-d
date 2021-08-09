package bbs.auth.model;

public class User {

	private String id;
	private String name;
	private String user;
	private String email;
	private String access_token;
	

	public User(String id, String name, String user) {
		this.id = id;
		this.name = name;
		this.user = user;
	}

	public User(String email, String access_token) {
		this.email = email;
		this.access_token = access_token;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
