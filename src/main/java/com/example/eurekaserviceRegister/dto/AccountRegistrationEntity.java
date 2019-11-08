package com.example.eurekaserviceRegister.dto;




public class AccountRegistrationEntity {
	
	
	private String userId;
	

	private String userName;
	
	
	private String password;
	

	private String email;

	
	@Override
	public String toString() {
		return "AccountRegistrationEntity [userId=" + userId + ", userName=" + userName + ", password=" + password
				+ ", email=" + email + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	
}
