package com.sbnz.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbnz.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

	RegisteredUser findByEmail(String email);
	
	@Query(value = "SELECT * FROM USERS WHERE TYPE = 'registered_user' AND ACTIVE = TRUE", nativeQuery = true)
	List<RegisteredUser> findAllRegisteredUser();

	Optional<RegisteredUser> findByIdAndActive(Long id, boolean b);

	RegisteredUser findByEmailAndActive(String email, boolean b);

}
