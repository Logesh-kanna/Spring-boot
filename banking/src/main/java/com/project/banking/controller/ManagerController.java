package com.project.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.RegisterDto;
import com.project.banking.service.Auth.EmployeeAuthService;
import com.project.banking.wrapper.HttpResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/admin")
public class ManagerController {

	@Autowired
	EmployeeAuthService employeeService;
	
	@PostMapping("register")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> signUp(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}
	
	@PostMapping("set_inactive")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> inActive(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}
	
	@PostMapping("set_active")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> activate(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}
	
	@PostMapping("delete")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<HttpResponse> delete(@Valid @RequestBody RegisterDto register) {
		return employeeService.register(register);
	}
}
