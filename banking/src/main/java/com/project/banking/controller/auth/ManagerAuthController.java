package com.project.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.LoginDto;
import com.project.banking.dto.RegisterDto;
import com.project.banking.service.Auth.ManagerAuthService;
import com.project.banking.wrapper.HttpResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/manager")
public class ManagerAuthController {

	@Autowired
	ManagerAuthService managerService;
	
	@PostMapping("register")
	public ResponseEntity<HttpResponse> signUp(@Valid @RequestBody RegisterDto register) {
		return managerService.register(register);
	}
	
	@PostMapping("login")
	public ResponseEntity<HttpResponse> signIn(@Valid @RequestBody LoginDto login) throws Exception {
		return managerService.login(login);
	}
	
}
