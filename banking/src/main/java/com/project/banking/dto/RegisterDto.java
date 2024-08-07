package com.project.banking.dto;

import jakarta.validation.constraints.NotNull;

public class RegisterDto {

	@NotNull(message = "name field is required")
	private String name;
	@NotNull(message = "address field is required")
	private String address;
	@NotNull(message = "number field is required")
	private Long number;
	@NotNull(message = "email field is required")
	private String email;
	@NotNull(message = "password field is required")
	private String password;

	public RegisterDto() {
		super();
	}

	public RegisterDto(String name, String address, Long number, String email, String password) {
		super();
		this.name = name;
		this.address = address;
		this.number = number;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
