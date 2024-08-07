package com.project.e_commerce.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.e_commerce.Model.CartItemModel;
import com.project.e_commerce.Model.CartModel;
import com.project.e_commerce.Model.PurchaseModel;
import com.project.e_commerce.Model.UserModel;
import com.project.e_commerce.Service.UserPurchaseService;

@RestController
@RequestMapping("api/user")
public class UserPurchaseController {
	
	@Autowired
	UserPurchaseService userPurchaseService;
	
	@PostMapping("purchase")
	@ResponseBody
	public ResponseEntity<String> purchase(@RequestBody PurchaseModel data) {
		return userPurchaseService.userPurchase(data.getCart_id(), data.getProduct_id());
	}
	
	@GetMapping("show_user_purchase")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> userPurchase(@RequestBody UserModel user) {
		return userPurchaseService.userPurchaseData(user.getId());
	}
	
}
