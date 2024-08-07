package com.project.e_commerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.e_commerce.Model.CartItemModel;
import com.project.e_commerce.Model.CartModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Integer> {

	List<CartItemModel> findByCart_Id(int cartId);
	
}
