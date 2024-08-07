package com.project.banking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account_details", uniqueConstraints = {@UniqueConstraint(columnNames = {"token"})})
public class AccountDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "token", unique = true)
	private String token;
	@Column(name = "account_number")
	private String number;
	@OneToOne
	@JoinColumn(name = "user_token", referencedColumnName = "token")
    private User user_token;

	public AccountDetail() {
		super();
	}

	public AccountDetail(int id, String token, String number, User user_token) {
		super();
		this.id = id;
		this.token = token;
		this.number = number;
		this.user_token = user_token;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser_token() {
		return user_token;
	}

	public void setUser_token(User user_token) {
		this.user_token = user_token;
	}

}
