package com.project.banking.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.project.banking.model.AccountDetail;

public interface AccountDetailsRepository extends JpaRepository<AccountDetail, Integer> {

	Optional<AccountDetail> findByNumber(String number);

	@Query("SELECT a FROM account_details a WHERE a.user_token = :token")
	Optional<AccountDetail> findByToken(String token);

}
