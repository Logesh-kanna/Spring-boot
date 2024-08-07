package com.project.banking.dto;

import jakarta.validation.constraints.NotNull;

public class RoleDto {

	@NotNull(message = "Name field is required")
	private String name;

	public RoleDto() {
		super();
	}

	public RoleDto(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
