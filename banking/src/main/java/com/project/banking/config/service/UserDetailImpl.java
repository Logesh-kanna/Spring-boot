package com.project.banking.config.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailImpl implements UserDetails {

	private static final long serialVersionUID = 4969746959778323372L;
	private int id;
	private String token;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authority;

	public UserDetailImpl() {
		super();
	}

	public UserDetailImpl(int id, String token, String email, String password, Collection<? extends GrantedAuthority> authority) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authority = authority;
		this.token = token;
	}

//	public UserDetailImpl build(User user) {
//		List<GrantedAuthority> authorities = user.getRole().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
//		return new UserDetailImpl(user.getId(), user.getEmail(), user.getPassword(), authorities);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}

}
