package com.faith.model;

public class Login {
	
	//instance variables
	private Integer loginId; 
	private String staffName; 
	private String username;
	private String password;
	private boolean status;
	
	//constructor
	public Login() {
		super();	
	}

	//getters and setters
	
	
	public Integer getLoginId() {
		return loginId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
