package com.back.dto;

public class UserRegistrationDTO extends UserDTO{
    private String password;
    private String repeatPassword;
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
    
    

}
