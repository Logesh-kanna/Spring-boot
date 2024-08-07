package com.project.jwt.Controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.jwt.Dto.RegisterDto;
import com.project.jwt.Dto.loginDto;
import com.project.jwt.Model.UserModel;
import com.project.jwt.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;

	@PostMapping("register")
	public String register(@RequestBody RegisterDto model) throws Exception {
		return service.signUp(model);
	}
	
	@PostMapping("login")
	public HashMap<String, Object> login(@RequestBody loginDto model) throws Exception {
		return service.signIn(model);
	}
	
	@GetMapping("user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String user() {
		return "Hello User!";
	}
	
	@GetMapping("admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder token = new StringBuilder();
		for(int i=0; i < 12; i++) {
			int index = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(index));
		}
		return token.toString();
	}
}
