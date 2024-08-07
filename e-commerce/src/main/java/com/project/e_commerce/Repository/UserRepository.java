package com.project.e_commerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.e_commerce.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	Optional<UserModel> findByEmail(String email);

}
