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
import com.project.banking.model.BlackListedToken;
import com.project.banking.model.Employee;
import com.project.banking.model.Status;
import com.project.banking.repository.BlackListedTokenRepository;
import com.project.banking.repository.EmployeeRepository;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeAuthService {

	@Autowired
	EmployeeRepository empolyeeRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwt;
	@Autowired
	BlackListedTokenRepository blackListedTokenRepo;
	@Autowired
	CommonUtils utils;

	public ResponseEntity<HttpResponse> register(RegisterDto data) {
		Employee exists = empolyeeRepo.findByEmail(data.getEmail()).orElse(new Employee());

		Employee tokenExist;
		String token;
		do {
			token = utils.generateToken();
			tokenExist = empolyeeRepo.findByToken(token).orElse(null);
		} while (tokenExist != null);

		if (exists != null) {
			Employee employee = new Employee();
			employee.setToken(token);
			employee.setName(data.getName());
			employee.setAddress(data.getAddress());
			employee.setNumber(data.getNumber());
			employee.setEmail(data.getEmail());
			employee.setStatus(Status.Active);
			employee.setPassword(passwordEncoder.encode(data.getPassword()));
			empolyeeRepo.save(employee);
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
		Employee employe = empolyeeRepo.findByEmail(userdetail.getUsername()).orElse(new Employee());
		if (employe != null) {
			HashMap<String, Object> employemap = new HashMap<>();
			employemap.put("id", employe.getId());
			employemap.put("token", token);
			employemap.put("name", employe.getName());
			employemap.put("email", employe.getEmail());
			employemap.put("address", employe.getAddress());
			employemap.put("number", employe.getNumber());
			list.add(employemap);
			return utils.success(200, "Success", list);
		} else {
			return utils.error(400, "User not found");
		}

	}

	

}
