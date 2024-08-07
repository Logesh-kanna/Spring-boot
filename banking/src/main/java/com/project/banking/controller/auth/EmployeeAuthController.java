package com.project.banking.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.LoginDto;
import com.project.banking.dto.RegisterDto;
import com.project.banking.service.Auth.EmployeeAuthService;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/employe")
public class EmployeeAuthController {
	
	@Autowired
	EmployeeAuthService employeeService;
	@Autowired
	CommonUtils utils;
	
	@PostMapping("register")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> signUp(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}
	
	@PostMapping("login")
	public ResponseEntity<HttpResponse> signIn(@Valid @RequestBody LoginDto login) throws Exception {
		return employeeService.login(login);
	}
	
	@PostMapping("logout")
	public ResponseEntity<HttpResponse> logout(HttpServletRequest request) {
		return utils.success(200, "Logout successfully");
	}
	
}
