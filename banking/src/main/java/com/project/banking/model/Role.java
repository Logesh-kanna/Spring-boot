package com.project.banking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"token"})})
public class Role {
	
	public Role(int id, Roles name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Role() {
		super();
	}

	public enum Roles {
		ROLE_MANAGER, ROLE_EMPLOYEE, ROLE_USER;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "token")
	private String token;
	
	@Enumerated(EnumType.STRING)
	private Roles name;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Roles getName() {
		return name;
	}

	public void setName(Roles name) {
		this.name = name;
	}
	
}
