package com.project.e_commerce.Model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserModel {

	public UserModel(int id, String name, String address, long number, int age, Gender gender, String email,
			String password, CartModel cart, Set<RoleModel> roles) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.number = number;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.cart = cart;
		this.roles = roles;
	}

	public UserModel() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "address", nullable = false, length = 150)
	private String address;

	@Column(name = "number", nullable = false, length = 15)
	private long number;

	@Column(name = "age", length = 5)
	private int age;

	public enum Gender {
		male, female
	};

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", columnDefinition = "TEXT")
	private String password;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private CartModel cart;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleModel> roles;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCart(CartModel cart) {
		this.cart = cart;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}