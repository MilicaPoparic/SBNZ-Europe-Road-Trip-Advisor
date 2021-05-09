package com.sbnz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	List<User> findAllByActive(boolean b);

	Optional<User> findByIdAndActive(Long id, boolean b);

	User findByEmailAndActive(String email, boolean b);

	Page<User> findByActive(Pageable pageable, boolean b);
}