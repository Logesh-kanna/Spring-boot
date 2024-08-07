package com.project.e_commerce.Model;

import java.util.HashMap;

public class LoginResponseModel {

	private Boolean status;
	private HashMap<String, Object> data;
	private String error;
	
	public LoginResponseModel() {
		
	}
	
	public LoginResponseModel(Boolean status, HashMap<String, Object> data) {
		this.status = status;
		this.data = data;
	}
	
	public LoginResponseModel(Boolean status, String error) {
		this.status = status;
		this.error = error;
	}

	public Boolean getStatus() {
		return status;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public String getError() {
		return error;
	}
	
	
}
