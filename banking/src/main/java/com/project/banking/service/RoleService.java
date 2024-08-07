package com.project.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.banking.dto.RoleDto;
import com.project.banking.model.Role;
import com.project.banking.model.Role.Roles;
import com.project.banking.repository.RoleRepository;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	CommonUtils utils;

	public ResponseEntity<HttpResponse> addRole(RoleDto data) throws Exception {

		Role role = new Role();
		role.setToken(utils.generateToken());
		if (data.getName().equals("manager")) {
			Role exists = roleRepo.findByName(Roles.ROLE_MANAGER).orElse(new Role());
			if (exists == null) {
				role.setName(Roles.ROLE_MANAGER);
			} else {
				return utils.error(400, "Role already exists");
			}
		} else if (data.getName().equals("employee")) {
			Role exists = roleRepo.findByName(Roles.ROLE_EMPLOYEE).orElse(new Role());
			if (exists == null) {
				role.setName(Roles.ROLE_EMPLOYEE);
			} else {
				return utils.error(400, "Role already exists");
			}
		} else if (data.getName().equals("user")) {
			Role exists = roleRepo.findByName(Roles.ROLE_USER).orElse(new Role());
			if (exists == null) {
				role.setName(Roles.ROLE_USER);
			} else {
				return utils.error(400, "Role already exists");
			}
		} else {
			return utils.error(400, "Invalid Role");
		}
		roleRepo.save(role);
		List<Object> list = new ArrayList<>();
		return utils.success(200, "Role created successfully", list);
	}

}
