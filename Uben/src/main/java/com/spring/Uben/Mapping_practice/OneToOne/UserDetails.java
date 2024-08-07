package com.spring.Uben.Mapping_practice.OneToOne;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user__details")
public class UserDetails {
	
	@Id
	private int id;
	@Column(name = "age")
	private int age;
	@Column(name = "address")
	private String address;
	@Column(name = "number")
	private String number;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

}
