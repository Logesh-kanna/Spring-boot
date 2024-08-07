package com.project.e_commerce.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private CartModel cart;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private ProductModel product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CartModel getCart_id() {
		return cart;
	}

	public void setCart_id(CartModel cart) {
		this.cart = cart;
	}

	public ProductModel getProduct_id() {
		return product;
	}

	public void setProduct_id(ProductModel product) {
		this.product = product;
	}

	public CartItemModel(int id, CartModel cart, ProductModel product) {
		this.id = id;
		this.cart = cart;
		this.product = product;
	}

	public CartItemModel() {
		
	}
	
}
