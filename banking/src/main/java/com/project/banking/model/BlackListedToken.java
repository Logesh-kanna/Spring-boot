package com.project.banking.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "black_listed_tokens")
public class BlackListedToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "user_token", referencedColumnName = "token")
	private User user_token;

	@OneToOne
	@JoinColumn(name = "manager_token", referencedColumnName = "token")
	private Manager manager_token;

	@OneToOne
	@JoinColumn(name = "employee_token", referencedColumnName = "token")
	private Employee employee_token;

	@Column(name = "bearer_token", columnDefinition = "TEXT")
	private String bearer_token;

	@Column(name = "expiry_date")
	private Date expiry_date;

	@Column(name = "created_at")
	private Date created_at;

	public BlackListedToken(int id, User user_token, Manager manager_token, Employee employee_token,
			String bearer_token, Date expiry_date, Date created_at) {
		super();
		this.id = id;
		this.user_token = user_token;
		this.manager_token = manager_token;
		this.employee_token = employee_token;
		this.bearer_token = bearer_token;
		this.expiry_date = expiry_date;
		this.created_at = created_at;
	}

	public BlackListedToken() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_token() {
		return user_token;
	}

	public void setUser_token(User user_token) {
		this.user_token = user_token;
	}

	public Manager getManager_token() {
		return manager_token;
	}

	public void setManager_token(Manager manager_token) {
		this.manager_token = manager_token;
	}

	public Employee getEmployee_token() {
		return employee_token;
	}

	public void setEmployee_token(Employee employee_token) {
		this.employee_token = employee_token;
	}

	public String getBearer_token() {
		return bearer_token;
	}

	public void setBearer_token(String bearer_token) {
		this.bearer_token = bearer_token;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
