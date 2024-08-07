package com.project.jwt.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.jwt.Config.jwt.JwtService;
import com.project.jwt.Config.jwt.Service.UserDetailImpl;
import com.project.jwt.Dto.RegisterDto;
import com.project.jwt.Dto.loginDto;
import com.project.jwt.Model.RoleModel;
import com.project.jwt.Model.RoleModel.Role;
import com.project.jwt.Model.UserModel;
import com.project.jwt.Repository.RoleRepository;
import com.project.jwt.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwt;

	public String signUp(RegisterDto userData) throws Exception {
		UserModel user = new UserModel();
		RoleModel role = new RoleModel();
		
		if(userData.getRole().equals("admin")) {
			role = roleRepo.findByName(Role.ROLE_ADMIN);
		}else {
			role = roleRepo.findByName(Role.ROLE_USER);
		}
		user.setUsername(userData.getUsername());
		user.setEmail(userData.getEmail());
		user.setPassword(passwordEncoder.encode(userData.getPassword()));
		Set<RoleModel> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userRepo.save(user);
		return "Success";
	}

	public HashMap<String, Object> signIn(loginDto loginData) throws Exception {
		Authentication authetication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authetication);
		String token = jwt.generateToken(authetication);
		
		UserDetailImpl userdetail = (UserDetailImpl) authetication.getPrincipal();
		List<String> role = userdetail.getAuthorities().stream()
				.map(item -> item.getAuthority()).collect(Collectors.toList());
		HashMap<String, Object> hash = new HashMap<>();
		hash.put("access_token", token);
		hash.put("email", userdetail.getUsername());
		hash.put("role", role);
		return hash;
	}

}
