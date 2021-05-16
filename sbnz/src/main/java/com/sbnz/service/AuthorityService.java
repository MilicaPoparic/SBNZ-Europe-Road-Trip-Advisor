package com.sbnz.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Authority;
import com.sbnz.repository.AuthorityRepository;

@Service
public class AuthorityService implements ServiceInterface<Authority>{

	@Autowired 
	private AuthorityRepository authorityRepository;
	
	@Override
	public List<Authority> findAll() {
		
		return authorityRepository.findAll();
	}

	@Override
	public Authority findOne(Long id) {
		return authorityRepository.findById(id).orElse(null);
	}

	@Override
	public Authority create(Authority entity) throws Exception {
		Authority a = new Authority();
		a.setRole(entity.getRole());
		return authorityRepository.save(a);
	}

	@Override
	public Authority update(Authority entity, Long id) throws Exception {
		Authority existingA = authorityRepository.findById(id).orElse(null);
		if(existingA == null) {
			throw new Exception("Authority with given id doesn't exist");
		}
		existingA.setRole(entity.getRole());
		return authorityRepository.save(existingA);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<Authority> optA = authorityRepository.findById(id);
		if(!optA.isPresent()) {
			throw new Exception("Authority with given id doesn't exist");
		}
		Authority existingA = optA.orElse(null);		
		authorityRepository.delete(existingA);		
		
	}
	public Authority findByRole(String role) throws Exception {
		return  authorityRepository.findByRole(role);
		
	}

    public Set<Authority> findById(Long id) {
        Authority auth = this.authorityRepository.getOne(id);
        Set<Authority> auths = new HashSet<Authority>();
        auths.add(auth);
        return auths;
    }

    public Set<Authority> findByName(String name) {
        Authority auth = this.authorityRepository.findByRole(name);
        Set<Authority> auths = new HashSet<Authority>();
        auths.add(auth);
        return auths;
    }

}
