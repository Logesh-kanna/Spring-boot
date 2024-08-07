package com.project.banking.service.Auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.banking.config.jwt.JwtService;
import com.project.banking.config.service.UserDetailImpl;
import com.project.banking.dto.LoginDto;
import com.project.banking.dto.RegisterDto;
import com.project.banking.model.Manager;
import com.project.banking.model.Status;
import com.project.banking.repository.ManagerRepository;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

@Service
public class ManagerAuthService {

	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwt;
	@Autowired
	CommonUtils utils;

	public ResponseEntity<HttpResponse> register(RegisterDto data) {
		Manager exists = managerRepo.findByEmail(data.getEmail()).orElse(new Manager());

		Manager tokenExist;
		String token;
		do {
			token = utils.generateToken();
			tokenExist = managerRepo.findByToken(token).orElse(null);
		} while (tokenExist != null);

		if (exists != null) {
			Manager manager = new Manager();
			manager.setToken(token);
			manager.setName(data.getName());
			manager.setAddress(data.getAddress());
			manager.setNumber(data.getNumber());
			manager.setEmail(data.getEmail());
			manager.setStatus(Status.Active);
			manager.setPassword(passwordEncoder.encode(data.getPassword()));
			managerRepo.save(manager);
			return utils.success(200, "User Created Successfully");
		} else {
			return utils.error(400, "User already exists");
		}
	}

	public ResponseEntity<HttpResponse> login(LoginDto data) throws Exception {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwt.generateToken(authentication);

		UserDetailImpl userdetail = (UserDetailImpl) authentication.getPrincipal();
		List<Object> list = new ArrayList<>();
		Manager manager = managerRepo.findByEmail(userdetail.getUsername()).orElse(new Manager());
		if (manager != null) {
			HashMap<String, Object> managermap = new HashMap<>();
			managermap.put("id", manager.getId());
			managermap.put("token", token);
			managermap.put("name", manager.getName());
			managermap.put("email", manager.getEmail());
			managermap.put("address", manager.getAddress());
			managermap.put("number", manager.getNumber());
			list.add(managermap);
			return utils.success(200, "Success", list);
		} else {
			return utils.error(400, "User not found");
		}

	}

}
