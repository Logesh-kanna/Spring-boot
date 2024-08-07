package com.project.e_commerce.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.e_commerce.Model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {
	
	CartModel findByUserId(int userId);

}
