package com.spring.Uben.User;

import java.util.HashMap;

public class LoginResult {
	
	private Boolean status;
	private HashMap<String, Object> data;
	private String error;
	
	
	public Boolean getStatus() {
		return status;
	}

	public HashMap<String, Object>  getData() {
		return data;
	}

	public String getError() {
		return error;
	}
	
	public LoginResult(Boolean status, HashMap<String, Object>  data) {
		this.status = status;
		this.data = data;
	}
	
	public LoginResult(Boolean status, String error) {
		this.status = status;
		this.error = error;
	}
	
	

}
