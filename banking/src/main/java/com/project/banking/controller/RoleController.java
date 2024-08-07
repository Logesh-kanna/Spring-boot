package com.project.banking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.banking.dto.RoleDto;
import com.project.banking.service.RoleService;
import com.project.banking.wrapper.HttpResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/role")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@PostMapping("add")
	public ResponseEntity<HttpResponse> addRole(@Valid @RequestBody RoleDto data) throws Exception {
		return roleService.addRole(data);
	}
	
}
