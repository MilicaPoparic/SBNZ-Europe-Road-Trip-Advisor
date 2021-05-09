package com.sbnz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.model.RegisteredUser;
import com.sbnz.repository.RegisteredUserRepository;

@Service
public class RegisteredUserService implements ServiceInterface<RegisteredUser> {

	@Autowired
	private RegisteredUserRepository repository;

//	@Autowired
//	private AuthorityRepository authorityRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public List<RegisteredUser> findAll() {
		return repository.findAllRegisteredUser();
	}

	@Override
	public RegisteredUser findOne(Long id) {
		return repository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public RegisteredUser create(RegisteredUser entity) throws Exception {
		// if admin already exists
		RegisteredUser ru = repository.findByEmail(entity.getEmail());
		if (ru != null)
			throw new Exception("User is already registered");

		RegisteredUser ruser = new RegisteredUser();
		ruser.setFirstName(entity.getFirstName());
		ruser.setLastName(entity.getLastName());
		ruser.setEmail(entity.getEmail());
//		ruser.setPassword(passwordEncoder.encode(entity.getPassword()));
		ruser.setActive(true);
		ruser.setVerified(false);
//		Set<Authority> set = new HashSet<Authority>();
//		set.add(authorityRepository.findByRole("ROLE_REGISTERED_USER"));
//		ruser.setAuthority(set);
		return repository.save(ruser);
	}

	@Override
	public RegisteredUser update(RegisteredUser entity, Long id) throws Exception {
		RegisteredUser u = repository.findById(id).orElse(null);
		if (u == null)
			throw new Exception("User doesn't registered");
		String oldEmail = u.getEmail();
		RegisteredUser checkRegisteredUser;
		if (!oldEmail.equals(entity.getEmail())) {
			checkRegisteredUser = repository.findByEmail(entity.getEmail());
			if (checkRegisteredUser != null) {
				throw new Exception("User with given email already exist");
			}
			u.setEmail(entity.getEmail());
		} else {
			u.setEmail(oldEmail);
		}
		u.setFirstName(entity.getFirstName());
		u.setLastName(entity.getLastName());
		// u.setPassword(passwordEncoder.encode(entity.getPassword()));
		return repository.save(u);
	}

	@Override
	public void delete(Long id) throws Exception {
		RegisteredUser a = repository.findById(id).orElse(null);
		if (a == null)
			throw new Exception("Registered user doesn't exist");
		a.setActive(false);
		a = repository.save(a);
	}

	public RegisteredUser findByEmail(String email) {
		return repository.findByEmailAndActive(email, true);
	}

}
