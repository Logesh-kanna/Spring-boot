package com.project.e_commerce.Model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public enum Role {
		ROLE_ADMIN, ROLE_USER;
	}

	@Enumerated(EnumType.STRING)
	private Role name;

	@ManyToMany(mappedBy = "roles")
	private Set<UserModel> users;

	public RoleModel(Integer id, Role name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getName() {
		return name;
	}

	public void setName(Role name) {
		this.name = name;
	}
}
