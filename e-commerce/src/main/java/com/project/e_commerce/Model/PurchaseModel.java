package com.project.e_commerce.Model;

public class PurchaseModel {

	private int cart_id;
	private int product_id;

	// Constructors
	public PurchaseModel() {
		
	}

	public PurchaseModel(int cart_id, int product_id) {
		this.cart_id = cart_id;
		this.product_id = product_id;
	}

	// Getters and setters
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

}
