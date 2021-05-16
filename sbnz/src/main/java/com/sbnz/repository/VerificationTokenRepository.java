package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.model.User;
import com.sbnz.model.VerificationToken;


public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Long>{
  
	  @Query(value = "SELECT * FROM verification_token WHERE token = ?1", nativeQuery = true)
	  VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);

}
