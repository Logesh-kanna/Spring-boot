package com.project.e_commerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.e_commerce.Model.ProductModel;
import com.project.e_commerce.Service.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("vendor/save")
	@ResponseBody
	public ResponseEntity<String> saveProduct(@RequestBody ProductModel products) {
		return productService.uploadProdects(products);
	}
	
	@GetMapping("show_all")
	@ResponseBody
	public ResponseEntity<List<ProductModel>> allProducts(){
		return productService.allProducts();
	}
	
	@GetMapping("show")
	@ResponseBody
	public ResponseEntity<ProductModel> showProduct(@RequestBody ProductModel products) {
		return productService.showById(products.getId());
	}
	
	@PostMapping("vendor/update")
	@ResponseBody
	public ResponseEntity<String> updateProduct(@RequestBody ProductModel products) {
		return productService.updateProduct(products.getId(), products);
	}
	
	@DeleteMapping("vendor/delete")
	@ResponseBody
	public ResponseEntity<String> deleteProducts(@RequestBody ProductModel product) {
		return productService.deleteProduct(product.getId());
	}
	
	
	
}
