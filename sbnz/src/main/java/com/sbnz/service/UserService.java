package com.sbnz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.model.User;
import com.sbnz.repository.UserRepository;

@Service
public class UserService implements ServiceInterface<User> {

	@Autowired
	private UserRepository repository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuthorityService authService;

	@Override
	public List<User> findAll() {
		return repository.findAllByActive(true);
	}

	@Override
	public User findOne(Long id) {
		return repository.findByIdAndActive(id, true).orElse(null);
	}

	public User findByEmail(String email) {
		return repository.findByEmailAndActive(email, true);
	}

	@Override
	public User create(User entity) throws Exception {
		return null;
	}

	@Override
	public User update(User entity, Long id) throws Exception {
		return null;
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	public Page<User> findAll(Pageable pageable) {
		return repository.findByActive(pageable, true);
	}


}
