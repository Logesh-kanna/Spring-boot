package com.project.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.banking.model.BlackListedToken;
import com.project.banking.model.Employee;

public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, Integer> {
	
	Optional<BlackListedToken> findByBearerToken(String bearer_token);
	
}
