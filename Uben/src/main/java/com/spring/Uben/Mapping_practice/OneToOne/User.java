package com.spring.Uben.Mapping_practice.OneToOne;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users__1")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private UserDetails userDetails;
	
	public User(String name) {
		this.name = name;
	}
	
	public User() {
		
	}
	
}
