package com.spring.Uben.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.Uben.User.jwt.jwtService;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder passwordencoder;

	@Autowired
	jwtService jwt;

	public String register(UserModel request) {
		UserModel user = new UserModel();
		user.setName(request.getName());
		user.setAge(request.getAge());
		user.setAddress(request.getAddress());
		user.setNumber(request.getNumber());
		user.setPassword(passwordencoder.encode(request.getPassword()));
//		repo.save(user);
		String jwtToken = jwt.generateToken(user);

		return jwtToken;
	}

	public LoginResult login(String name, String password) {
		UserModel data = repo.findByName(name);
		if (data != null) {
			Boolean matches = passwordencoder.matches(password, data.getPassword());
			if (matches == true) {
				HashMap<String, Object> user = new HashMap<>();
				user.put("id", data.getId());
				user.put("name", data.getName());
				user.put("age", data.getAge());
				user.put("number", data.getNumber());
				user.put("address", data.getAddress());
				String jwtToken = jwt.generateToken(data);
				user.put("access_token", jwtToken);
				return new LoginResult(true, user);
			}else {
				return new LoginResult(false, "Invalid Credential");
			}
		} else {
			return new LoginResult(false, "User not found");
		}
	}

	public List<UserModel> showAllUser() {
		return repo.findAll();
	}

	public UserModel showUser(int id) {
		UserModel user = repo.findById(id).orElse(new UserModel());
		return user;
	}

	public void updateUser(int id, UserModel model) {
		UserModel optionalUser = repo.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));

		optionalUser.setName(model.getName());
		optionalUser.setAge(model.getAge());
		optionalUser.setAddress(model.getAddress());
		optionalUser.setNumber(model.getNumber());
		repo.save(optionalUser);
	}

	public void delete(int Id) {
		repo.deleteById(Id);
	}

}
