package com.sbnz.service;

import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sbnz.dto.LoginEventDTO;
import com.sbnz.event.MultipleLoginFailedEvent;
import com.sbnz.model.RegisteredUser;
import com.sbnz.model.User;
import com.sbnz.repository.UserRepository;
import com.sbnz.verification_handler.OnAccessLinkEvent;

@Service
public class UserService implements ServiceInterface<User> {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuthorityService authService;
	
	@Autowired
	private KieSessionService kieSessionService;

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

	public void repeatVerfication(String username) {
		RegisteredUser user = (RegisteredUser) this.findByEmail(username);
		if (user != null) {
			user.setVerified(false);
			this.repository.save(user);
			eventPublisher
			.publishEvent(new OnAccessLinkEvent(user, null, ""));
		}
		
	}
	
	public void loginFailed(String username) {
		LoginEventDTO event = new LoginEventDTO(username);
		KieSession kieSession = kieSessionService.getCepSession();
		kieSession.insert(event);
		kieSession.getAgenda().getAgendaGroup("login").setFocus();
		kieSession.fireAllRules();
		Collection<?> result = kieSession.getObjects(new ClassObjectFilter(MultipleLoginFailedEvent.class));
		if (result.size()>0) {
			MultipleLoginFailedEvent res = (MultipleLoginFailedEvent) result.iterator().next();
			if (res.getUsername().equals(username)) {
				this.repeatVerfication(username);
			}
		}
	}


}
