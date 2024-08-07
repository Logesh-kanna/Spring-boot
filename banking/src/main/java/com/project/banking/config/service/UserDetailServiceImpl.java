package com.project.banking.config.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.banking.model.Employee;
import com.project.banking.model.Manager;
import com.project.banking.model.User;
import com.project.banking.repository.EmployeeRepository;
import com.project.banking.repository.ManagerRepository;
import com.project.banking.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	EmployeeRepository employeeRepo;

	public UserDetails loadUserByUsername(String email) {
		User user = userRepo.findByEmail(email).orElse(null);
		Manager manager = managerRepo.findByEmail(email).orElse(null);
		Employee employee = employeeRepo.findByEmail(email).orElse(null);

		if (user != null) {
			UserDetailImpl userdetail = new UserDetailImpl(user.getId(), user.getToken(), user.getEmail(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
			return userdetail;
		} else if (manager != null) {
			UserDetailImpl userdetail = new UserDetailImpl(manager.getId(), manager.getToken(), manager.getEmail(), manager.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER")));
			return userdetail;
		} else if (employee != null) {
			UserDetailImpl userdetail = new UserDetailImpl(employee.getId(), employee.getToken(), employee.getEmail(), employee.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_EMPLOYEE")));
			return userdetail;
		}
		throw new UsernameNotFoundException("User not found for this email: " + email);

	}

}
