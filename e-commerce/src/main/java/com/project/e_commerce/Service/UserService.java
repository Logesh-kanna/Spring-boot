package com.project.e_commerce.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.e_commerce.Configure.Jwt.JwtService;
import com.project.e_commerce.Configure.Jwt.Service.UserDetailImpl;
import com.project.e_commerce.Dto.RegisterDto;
import com.project.e_commerce.Model.CartModel;
import com.project.e_commerce.Model.LoginResponseModel;
import com.project.e_commerce.Model.RoleModel;
import com.project.e_commerce.Model.RoleModel.Role;
import com.project.e_commerce.Model.UserModel;
import com.project.e_commerce.Model.UserModel.Gender;
import com.project.e_commerce.Repository.CartRepository;
import com.project.e_commerce.Repository.RoleRepository;
import com.project.e_commerce.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwt;

	public ResponseEntity<String> save(RegisterDto data) {
		UserModel user = new UserModel();
		RoleModel role = new RoleModel();

		if (data.getRole().equals("admin")) {
			role = roleRepo.findByName(Role.ROLE_ADMIN);
		} else {
			role = roleRepo.findByName(Role.ROLE_USER);
		}
		user.setName(data.getName());
		user.setAge(data.getAge());
		Gender gender;
		if (data.getGender().equals("male")) {
			gender = Gender.male;
		} else {
			gender = Gender.female;
		}
		user.setGender(gender);
		Set<RoleModel> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setNumber(data.getNumber());
		user.setAddress(data.getAddress());
		user.setEmail(data.getEmail());
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		userRepo.save(user);
		UserModel user_id = user;
		CartModel cart = new CartModel();
		String name = "user_" + data.getName().toLowerCase() + "_cart";
		cart.setName(name);
		cart.setUser(user_id);
		cartRepo.save(cart);
		return new ResponseEntity<>("User saved successfully", HttpStatus.OK);
	}

	public ResponseEntity<?> login(String email, String password) throws Exception {

		Authentication authetication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		SecurityContextHolder.getContext().setAuthentication(authetication);
		String token = jwt.generateToken(authetication);

		UserDetailImpl userdetail = (UserDetailImpl) authetication.getPrincipal();
		List<String> role = userdetail.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		UserModel userdata = userRepo.findByEmail(userdetail.getUsername())
				.orElseThrow(() -> new Exception("Email id not found"));
		LoginResponseModel response = new LoginResponseModel();
		if (userdata != null) {
			CartModel cart = cartRepo.findByUserId(userdata.getId());
			Boolean passwordMatches = passwordEncoder.matches(password, userdata.getPassword());
			if (passwordMatches == true) {
				HashMap<String, Object> user = new HashMap<>();
				user.put("id", userdata.getId());
				user.put("name", userdata.getName());
				user.put("email", userdata.getEmail());
				user.put("age", userdata.getAge());
				user.put("gender", userdata.getGender());
				user.put("role", userdata.getRoles());
				user.put("number", userdata.getNumber());
				user.put("address", userdata.getAddress());
				HashMap<String, Object> cartData = new HashMap<>();
				cartData.put("id", cart.getId());
				cartData.put("name", cart.getName());
				user.put("cart", cartData);
				user.put("access_token", token);
				response = new LoginResponseModel(true, user);
			} else {
				response = new LoginResponseModel(false, "Invalid Credential");
			}
		} else {
			response = new LoginResponseModel(false, "Email not found");
		}

		if (response.getStatus() == true) {
			return new ResponseEntity<>(response.getData(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response.getError(), HttpStatus.OK);
		}
	}

	public ResponseEntity<List<Object>> showAllData() {
		List<UserModel> users = userRepo.findAll();
		List<CartModel> carts = cartRepo.findAll();

		List<Object> array = new ArrayList<>();
		for (UserModel user : users) {
			HashMap<String, Object> data = new HashMap<>();
			data.put("id", user.getId());
			data.put("name", user.getName());
			data.put("age", user.getAge());
			data.put("email", user.getEmail());
			data.put("number", user.getNumber());
			data.put("address", user.getAddress());
			for (CartModel cart : carts) {
				if (cart.getUser() == user) {
					HashMap<String, Object> cartData = new HashMap<>();
					cartData.put("id", cart.getId());
					cartData.put("name", cart.getName());
					data.put("cart", cartData);
				}
			}
			array.add(data);
		}
		return new ResponseEntity<List<Object>>(array, HttpStatus.OK);

	}

	public ResponseEntity<HashMap<String, Object>> showData(int id) {
		UserModel user = userRepo.findById(id).orElse(new UserModel());
		CartModel cart = cartRepo.findByUserId(id);

		HashMap<String, Object> data = new HashMap<>();
		data.put("id", user.getId());
		data.put("name", user.getName());
		data.put("age", user.getAge());
		data.put("email", user.getEmail());
		data.put("number", user.getNumber());
		data.put("address", user.getAddress());
		HashMap<String, Object> cartData = new HashMap<>();
		cartData.put("id", cart.getId());
		cartData.put("name", cart.getName());
		data.put("cart", cartData);
		return new ResponseEntity<HashMap<String, Object>>(data, HttpStatus.OK);
	}

}
