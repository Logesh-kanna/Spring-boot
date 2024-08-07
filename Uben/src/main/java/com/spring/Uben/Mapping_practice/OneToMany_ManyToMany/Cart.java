package com.spring.Uben.Mapping_practice.OneToMany_ManyToMany;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
//	@ManyToOne()
//	@JoinColumn(name = "cart_id")
//	private Items item;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<Items> item;
	
	@ManyToMany
	@JoinTable(name = "purchase_logs", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
	private List<Cart> cart;
}
