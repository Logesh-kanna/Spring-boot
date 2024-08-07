package com.project.e_commerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.e_commerce.Model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	
	

}
