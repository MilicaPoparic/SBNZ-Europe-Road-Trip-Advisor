package com.sbnz.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.model.Admin;
import com.sbnz.repository.AdminRepository;


@Service
public class AdminService implements ServiceInterface<Admin>{

	@Autowired 
	private AdminRepository repository;
	
//	@Autowired 
//	private AuthorityRepository authorityRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Admin> findAll() {
		return repository.findAllAdmin();
	}

	@Override
	public Admin findOne(Long id) {
		return repository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public Admin create(Admin entity) throws Exception {
		Admin admin = repository.findByEmail(entity.getEmail());
		if (admin != null)
			throw new Exception("Admin with given email already exist");
		Admin a = new Admin();
		a.setFirstName(entity.getFirstName());
		a.setLastName(entity.getLastName());
		a.setEmail(entity.getEmail());
//		a.setPassword(passwordEncoder.encode(entity.getPassword()));
		a.setActive(true);
		a.setVerified(true);
//		Set<Authority> set = new HashSet<Authority>();
//		set.add(authorityRepository.findByRole("ROLE_ADMIN"));
//		a.setAuthority(set);		
        return repository.save(a);

	}

	
	@Override
	public Admin update(Admin entity, Long id) throws Exception {
		Admin a = repository.findById(id).orElse(null);
		if(a == null)
			throw new Exception("Admin with given id doesn't exist");
		validateAttributes(a);	
		String oldEmail = a.getEmail();
		Admin checkAdmin;
		if(!oldEmail.equals(entity.getEmail())) {
			checkAdmin = repository.findByEmail(entity.getEmail());
		    if(checkAdmin != null)
		    	throw new Exception("User with given email already exist");
		    a.setEmail(entity.getEmail());
		}
		else {
			a.setEmail(oldEmail);
		}
		a.setFirstName(entity.getFirstName());
		a.setLastName(entity.getLastName());
		//a.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		return repository.save(a);
	}

	private void validateAttributes(Admin a) throws Exception {
		if(a.getFirstName() == null || a.getFirstName().isEmpty())
			throw new Exception("Admin's first name is empty.");
		if(a.getLastName() == null || a.getLastName().isEmpty())
			throw new Exception("Admin's last name is empty.");
		if(a.getEmail() == null || a.getEmail().isEmpty())
			throw new Exception("Admin's email is empty.");
		if(a.getPassword() == null || a.getPassword().isEmpty())
			throw new Exception("Admin's password is empty.");
	}

	@Override
	public void delete(Long id) throws Exception {
		Admin a = repository.findById(id).orElse(null);
		if(a == null)
			throw new Exception("Admin doesn't exist");
		a.setActive(false);
		a = repository.save(a);
	}
	
	public Admin findOneChecker(Long id) {
		return repository.findById(id).orElse(null);
	}


}
