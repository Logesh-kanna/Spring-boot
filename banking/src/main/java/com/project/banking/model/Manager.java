package com.project.banking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "manager", uniqueConstraints = {@UniqueConstraint(columnNames = {"token"})})
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "token")
	private String token;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "number")
	private Long number;
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private Status status;
	
	public Manager(int id, String token, String name, String address, String email, String password, Long number, Status status) {
		super();
		this.id = id;
		this.token = token;
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.number = number;
		this.status = status;
	}

	public Manager() {
		super();
	}

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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
