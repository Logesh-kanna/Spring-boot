package com.project.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.LoginDto;
import com.project.banking.dto.RegisterDto;
import com.project.banking.service.Auth.UserAuthService;
import com.project.banking.wrapper.HttpResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/user")
public class UserAuthController {

	@Autowired
	UserAuthService userService;
	
	@PostMapping("register")
	public ResponseEntity<HttpResponse> signUp(@Valid @RequestBody RegisterDto register) {
		return userService.register(register);
	}
	
	@PostMapping("login")
	public ResponseEntity<HttpResponse> signIn(@Valid @RequestBody LoginDto login) throws Exception {
		return userService.login(login);
	}
	
}
