package com.project.e_commerce.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.e_commerce.Dto.LoginDto;
import com.project.e_commerce.Dto.RegisterDto;
import com.project.e_commerce.Model.UserModel;
import com.project.e_commerce.Service.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("register")
	@ResponseBody
	public ResponseEntity<String> createUser(@RequestBody RegisterDto user) {
		return userService.save(user);
	}
	
	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<?> userlogin(@RequestBody LoginDto logindata) throws Exception {
		return userService.login(logindata.getEmail(), logindata.getPassword());
	}
	
	@GetMapping("admin/list_all")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Object>>getAllUser() {
		return userService.showAllData();
	}
	
	@GetMapping("user")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<HashMap<String, Object>> getUser(@RequestBody UserModel user) {
		return userService.showData(user.getId());
	}
	
	
}
