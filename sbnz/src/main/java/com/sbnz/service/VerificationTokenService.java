package com.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sbnz.model.User;
import com.sbnz.model.VerificationToken;
import com.sbnz.repository.UserRepository;
import com.sbnz.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
	
	@Autowired 
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired 
	private UserRepository userRepository;
	
	public VerificationToken save(VerificationToken token) {
		return verificationTokenRepository.save(token);
	}
	
	public VerificationToken findByToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}
	public VerificationToken findByUser(User user) {
		return verificationTokenRepository.findByUser(user);
	}

	public void save(User user) {
		userRepository.save(user);		
	}	

}
