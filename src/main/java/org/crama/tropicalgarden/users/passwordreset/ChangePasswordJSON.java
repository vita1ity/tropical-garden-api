package org.crama.tropicalgarden.users.passwordreset;

import java.io.Serializable;

public class ChangePasswordJSON implements Serializable {

	private static final long serialVersionUID = -7224582277589835203L;

	private long id;
	
	private String token;
	
	private String password;
	
	private String confirmPassword;

	
	
	public ChangePasswordJSON() {
		super();
	}

	public ChangePasswordJSON(long id, String token, String password, String confirmPassword) {
		super();
		this.id = id;
		this.token = token;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
	
	
}
