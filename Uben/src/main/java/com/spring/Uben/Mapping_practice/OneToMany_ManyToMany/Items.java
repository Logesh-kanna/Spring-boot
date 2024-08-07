package com.spring.Uben.Mapping_practice.OneToMany_ManyToMany;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "items")
public class Items {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private float price;
	@Column(name = "about")
	private String about;
//	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//	@JoinColumn(name = "cart")
//	private List<Cart> cart;
	
	@ManyToOne()
	@JoinColumn(name = "cart_id")
	private Cart cart;

}
