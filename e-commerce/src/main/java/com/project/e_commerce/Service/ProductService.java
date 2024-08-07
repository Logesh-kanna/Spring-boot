package com.project.e_commerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.e_commerce.Model.ProductModel;
import com.project.e_commerce.Repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	public ResponseEntity<String> uploadProdects(ProductModel product) {
		productRepo.save(product);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<ProductModel>> allProducts() {
		return new ResponseEntity<List<ProductModel>>(productRepo.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<ProductModel> showById(int id) {
		ProductModel product = productRepo.findById(id).orElse(new ProductModel());
		return new ResponseEntity<ProductModel>(product, HttpStatus.OK);
	}
	
	public ResponseEntity<String> updateProduct(int id, ProductModel product) {
		ProductModel data = productRepo.findById(id).orElse(new ProductModel());
		data.setName(product.getName());
		data.setAbout(product.getAbout());
		data.setPrice(product.getPrice());
		productRepo.save(data);
		return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteProduct(int id) {
		productRepo.deleteById(id);
		return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
	}

}
