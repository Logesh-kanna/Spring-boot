package com.project.jwt.Config.jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.jwt.Model.UserModel;
import com.project.jwt.Repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email id not found"));
		return UserDetailImpl.build(user);
	}

}
