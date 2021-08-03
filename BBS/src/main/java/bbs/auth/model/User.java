package bbs.auth.model;

public class User {

	private String id;
	private String name,user;

	public User(String id, String name, String user) {
		this.id = id;
		this.name = name;
		this.user = user;
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
}
