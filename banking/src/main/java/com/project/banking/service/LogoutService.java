package com.project.banking.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.banking.config.jwt.JwtService;
import com.project.banking.model.BlackListedToken;
import com.project.banking.model.Employee;
import com.project.banking.model.Manager;
import com.project.banking.model.User;
import com.project.banking.repository.BlackListedTokenRepository;
import com.project.banking.repository.EmployeeRepository;
import com.project.banking.repository.ManagerRepository;
import com.project.banking.repository.UserRepository;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LogoutService {

	@Autowired
	EmployeeRepository empolyeeRepo;
	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	JwtService jwt;
	@Autowired
	BlackListedTokenRepository blackListedTokenRepo;
	@Autowired
	CommonUtils utils;

	public ResponseEntity<HttpResponse> logout(HttpServletRequest request) {

		String authHeader = request.getHeader("Authorization");
		String email = null;
		String token = null;
		Date expiry = null;
		Date currentDate = new Date();

		if (authHeader != null && authHeader.contains("Bearer ")) {
			token = authHeader.substring(7);
			email = jwt.extractUserName(token);
			expiry = jwt.extractExpiration(token);
		}
		if (email != null) {
			Manager manager = managerRepo.findByEmail(email).orElse(null);
			Employee employe = empolyeeRepo.findByEmail(email).orElse(null);
			User user = userRepo.findByEmail(email).orElse(null);
			if (manager != null) {
				BlackListedToken logoutToken = new BlackListedToken();
				logoutToken.setManager_token(manager);
				logoutToken.setBearer_token(token);
				logoutToken.setExpiry_date(expiry);
				logoutToken.setCreated_at(currentDate);
			} else if (employe != null) {
				BlackListedToken logoutToken = new BlackListedToken();
				logoutToken.setEmployee_token(employe);
				logoutToken.setBearer_token(token);
				logoutToken.setExpiry_date(expiry);
				logoutToken.setCreated_at(currentDate);
			} else if (user != null) {
				BlackListedToken logoutToken = new BlackListedToken();
				logoutToken.setUser_token(user);
				logoutToken.setBearer_token(token);
				logoutToken.setExpiry_date(expiry);
				logoutToken.setCreated_at(currentDate);
			} else {
				return utils.error(200, "UnAuthenticated");
			}
		}
		return utils.error(200, "UnAuthenticated");
	}

}
