package bbs.member.service;

import java.util.Map;

import bbs.util.ErrorUtil;

public class JoinRequest {

	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	private String email;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void validate(Map<String, Boolean> errors) {
		ErrorUtil.checkEmpty(errors, id, "id");
		ErrorUtil.checkEmpty(errors, name, "name");
		ErrorUtil.checkEmpty(errors, password, "password");
		ErrorUtil.checkEmpty(errors, confirmPassword, "confirmPassword");
		ErrorUtil.checkEmpty(errors, email, "email");
		
		if (errors.containsKey("confirmPassword") == false) {
			if (isPasswordEqualToConfirm() == false) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}

	private boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPassword);
	}
}
