package com.project.banking.config.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.banking.config.service.UserDetailServiceImpl;
import com.project.banking.model.BlackListedToken;
import com.project.banking.model.Employee;
import com.project.banking.model.Manager;
import com.project.banking.model.User;
import com.project.banking.repository.BlackListedTokenRepository;
import com.project.banking.repository.EmployeeRepository;
import com.project.banking.repository.ManagerRepository;
import com.project.banking.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtService jwt;
	@Autowired
	UserDetailServiceImpl userDetailService;
	@Autowired
	BlackListedTokenRepository blackListedTokenRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	ManagerRepository managerRepo;
	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if (authHeader != null && authHeader.contains("Bearer ")) {
			token = authHeader.substring(7);
			username = jwt.extractUserName(token);
		}

		BlackListedToken blackListed = blackListedTokenRepo.findByBearerToken(token).orElse(null);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && blackListed == null) {
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
			Manager manager = managerRepo.findByEmail(username).orElse(null);
			Employee employee = employeeRepo.findByEmail(username).orElse(null);
			User user = userRepo.findByEmail(username).orElse(null);
			String email;
			if (manager != null) {
				email = manager.getEmail();
				System.out.println("manager");
			} else if (employee != null) {
				email = employee.getEmail();
				System.out.println("employee");
			} else if (user != null) {
				email = user.getEmail();
				System.out.println("user");
			} else {
				email = "";
				System.out.println("data not found");
			}
			if (jwt.validateToken(token, email)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
