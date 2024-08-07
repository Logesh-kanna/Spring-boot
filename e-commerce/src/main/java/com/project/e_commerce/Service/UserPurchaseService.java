package com.project.e_commerce.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.e_commerce.Model.CartItemModel;
import com.project.e_commerce.Model.CartModel;
import com.project.e_commerce.Model.ProductModel;
import com.project.e_commerce.Repository.CartItemRepository;
import com.project.e_commerce.Repository.CartRepository;
import com.project.e_commerce.Repository.ProductRepository;

@Service
public class UserPurchaseService {

	@Autowired
	CartItemRepository cartItemRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	ProductRepository productRepo;

	public ResponseEntity<String> userPurchase(int cart_id, int product_id) {
		CartModel cart = cartRepo.findById(cart_id).orElseThrow(() -> new RuntimeException("Cart not found"));
		ProductModel product = productRepo.findById(product_id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		CartItemModel data = new CartItemModel();
		data.setCart_id(cart);
		data.setProduct_id(product);
		cartItemRepo.save(data);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

//	public ResponseEntity<HashMap<String, Object>> userPurchaseData(int user_id) {
//
//		CartModel cart = cartRepo.findByUserId(user_id);
//		List<CartItemModel> items = cartItemRepo.findAll();
//		List<ProductModel> products = productRepo.findAll();
//
//		HashMap<String, Object> data = new HashMap<>();
//		List<Object> obj = new ArrayList<>();
//		for (CartItemModel item : items) {
//			if (item.getCart_id() == cart) {
//				data.put("cart_name", cart.getName());
//				obj.add(item.getProduct_id());
//			}
//			
//		}
//		data.put("products", obj);
//		
//		for (ProductModel product : products) {
//			List<Object> cartList = new ArrayList<>();
////			if (item.getProduct_id() == product) {
//				HashMap<String, Object> productData = new HashMap<>();
//				productData.put("id", product.getId());
//				productData.put("name", product.getName());
//				productData.put("price", product.getPrice());
//				productData.put("about", product.getAbout());
//				cartList.add(productData);
//				data.put("products", cartList);
////			}
//		}
//
//		return new ResponseEntity<HashMap<String, Object>>((HashMap<String, Object>) obj, HttpStatus.OK);
//	}
	
	public ResponseEntity<HashMap<String, Object>> userPurchaseData(int user_id) {

		CartModel cart = cartRepo.findByUserId(user_id);
		List<CartItemModel> items = cartItemRepo.findAll();
		List<ProductModel> products = productRepo.findAll();

		HashMap<String, Object> data = new HashMap<>();
		List<Object> obj = new ArrayList<>();
		for (CartItemModel item : items) {
			if (item.getCart_id() == cart) {
				data.put("cart_name", cart.getName());
				obj.add(item.getProduct_id());
			}
			
		}
		data.put("products", obj);

		return new ResponseEntity<HashMap<String, Object>>(data, HttpStatus.OK);
	}
}

