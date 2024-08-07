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
import com.project.banking.model.AccountDetail;
import com.project.banking.model.Status;
import com.project.banking.model.User;
import com.project.banking.repository.AccountDetailsRepository;
import com.project.banking.repository.UserRepository;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

@Service
public class UserAuthService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AccountDetailsRepository accRepo;
	@Autowired
	JwtService jwt;
	@Autowired
	CommonUtils utils;

	public ResponseEntity<HttpResponse> register(RegisterDto data) {
		User exists = userRepo.findByEmail(data.getEmail()).orElse(new User());

		User tokenExist;
		String token;
		do {
			token = utils.generateToken();
			tokenExist = userRepo.findByToken(token).orElse(null);
		} while (tokenExist != null);

		AccountDetail account;
		String acc_no;
		do {
			acc_no = utils.generateAccountNumber();
			account = accRepo.findByNumber(acc_no).orElse(null);
		} while (account != null);

		if (exists != null) {
			User user = new User();
			user.setToken(token);
			user.setName(data.getName());
			user.setAddress(data.getAddress());
			user.setNumber(data.getNumber());
			user.setEmail(data.getEmail());
			user.setPassword(passwordEncoder.encode(data.getPassword()));
			user.setStatus(Status.Active);
			User savedUser = userRepo.save(user);

			AccountDetail userAccount = new AccountDetail();
			AccountDetail accTokenExist;
			String acctoken;
			do {
				acctoken = utils.generateToken();
				accTokenExist = accRepo.findByToken(token).orElse(null);
			} while (accTokenExist != null);

			userAccount.setNumber(acc_no);
			userAccount.setToken(acctoken);
			userAccount.setUser_token(savedUser);
			accRepo.save(userAccount);

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
		User user = userRepo.findByEmail(userdetail.getUsername()).orElse(new User());
		if (user != null) {
			HashMap<String, Object> usermap = new HashMap<>();
			usermap.put("id", user.getId());
			usermap.put("token", user.getToken());
			usermap.put("access_token", token);
			usermap.put("name", user.getName());
			usermap.put("email", user.getEmail());
			usermap.put("address", user.getAddress());
			usermap.put("number", user.getNumber());
			AccountDetail accountData = accRepo.findByToken(user.getToken()).orElse(new AccountDetail());
			HashMap<String, Object> account = new HashMap<>();
			account.put("id", accountData.getId());
			account.put("token", accountData.getToken());
			account.put("number", accountData.getNumber());
			usermap.put("account_detail", accountData);
			list.add(usermap);
			return utils.success(200, "Success", list);
		} else {
			return utils.error(400, "User not found");
		}

	}

}
