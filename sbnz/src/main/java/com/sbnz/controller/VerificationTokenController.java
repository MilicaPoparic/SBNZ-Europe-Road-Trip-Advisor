package com.sbnz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.model.User;
import com.sbnz.model.VerificationToken;
import com.sbnz.service.VerificationTokenService;

@RestController
@RequestMapping(value="/api/verification")
public class VerificationTokenController {
	
	@Autowired
	private VerificationTokenService verificationService;
	
	
	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public String confirmRegistration(@PathVariable String token,HttpServletRequest request) {
      
		VerificationToken verificationToken = verificationService.findByToken(token);
		if(verificationToken == null)
		{
			return "redirect: access denied";
		}
		User user = verificationToken.getUser();
		
		user.setVerified(true);
		verificationService.save(user);

		return "ok";
	}
}
