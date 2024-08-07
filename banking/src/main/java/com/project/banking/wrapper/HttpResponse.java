package com.project.banking.wrapper;

import java.util.List;

import org.springframework.http.HttpStatusCode;

public class HttpResponse {

	private int status;
	private String message;
	private List<Object> data;
	
	public HttpResponse(int status, String message, List<Object> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	
}
