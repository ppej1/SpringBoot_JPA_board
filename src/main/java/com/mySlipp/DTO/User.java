package com.mySlipp.DTO;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("User [userId=%s, password=%s, name=%s, email=%s]", userId, password, name, email);
	}
}
