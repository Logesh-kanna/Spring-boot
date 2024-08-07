package com.project.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.RegisterDto;
import com.project.banking.service.Auth.EmployeeAuthService;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class dummy {

	@Autowired
	EmployeeAuthService employeeService;
	
	@Autowired
	CommonUtils utils;
	
	@GetMapping("employe")
	public String employe() {
		return utils.generateAccountNumber();
	}
	
	@GetMapping("admin")
	@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
	public String admin() {
		return "Hello Manager!";
	}
	
	@PostMapping("register")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> signUp(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}

	
}
