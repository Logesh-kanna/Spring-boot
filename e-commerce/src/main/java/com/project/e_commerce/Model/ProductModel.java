package com.project.e_commerce.Model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "price",  nullable = false)
	private float price;
	
	@Column(name = "about_product",  nullable = false, columnDefinition = "TEXT")
	private String about;
	
	@OneToMany(mappedBy = "product")
	private List<CartItemModel> cartItem = new ArrayList<>();
	
	public ProductModel(int id, String name, float price, String about) {
		this.name = name;
		this.price = price;
		this.about = about;
	}
	
	public ProductModel() {
		
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	

}
