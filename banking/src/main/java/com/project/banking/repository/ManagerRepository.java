package com.project.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.banking.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	Optional<Manager> findByEmail(String email);
	
	Optional<Manager> findByToken(String token);

}
