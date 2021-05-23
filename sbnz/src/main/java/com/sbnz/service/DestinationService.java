package com.sbnz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sbnz.dto.DestinationDTO;
import com.sbnz.dto.SearchDTO;
import com.sbnz.model.Destination;
import com.sbnz.model.RegisteredUser;
import com.sbnz.repository.DestinationRepository;
import com.sbnz.repository.RegisteredUserRepository;

@Service
public class DestinationService implements ServiceInterface<Destination> {

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private RegisteredUserRepository registeredUserRepository;

	@Autowired
	private KieContainer kieContainer;

	private static final Logger logger = LoggerFactory.getLogger(DestinationService.class);

	@Override
	public List<Destination> findAll() {
		return destinationRepository.findByActive(true);
	}

	@Override
	public Destination findOne(Long id) {
		return destinationRepository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public Destination create(Destination entity) throws Exception {
		// make new Destination instance
		Destination c = new Destination();
		c.setName(entity.getName());
		c.setLocationLat(entity.getLocationLat());
		c.setLocationLon(entity.getLocationLon());
		c.setLocalFood(entity.getLocalFood());
		c.setCategories(entity.getCategories());
		c.setHotels(entity.getHotels());
		c.setScore(entity.getScore());
		c.setTransportation(entity.getTransportation());
		c.setTrending(entity.getTrending());
		c.setActive(true);
		c = destinationRepository.save(c);
		return c;
	}

	@Override
	public Destination update(Destination entity, Long id) throws Exception {
		Optional<Destination> optDestination = destinationRepository.findById(id);
		if (!optDestination.isPresent()) {
			throw new Exception("Destination with given id doesn't exist");
		}
		Destination existingDestination = optDestination.orElse(null);

		existingDestination.setName(entity.getName());
		return destinationRepository.save(existingDestination);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<Destination> optDestination = destinationRepository.findById(id);
		if (!optDestination.isPresent()) {
			throw new Exception("Destination with given id doesn't exist");
		}
		Destination existingDestination = optDestination.orElse(null);
		existingDestination.setActive(false);
		destinationRepository.save(existingDestination);
	}

	public List<Destination> findAllActive() {
		return destinationRepository.findByActive(true);
	}

	public Optional<Destination> findDestinationById(Long id) {
		return destinationRepository.findByIdAndActive(id, true);
	}

	public List<Destination> filterByUserProfile() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		RegisteredUser ru = registeredUserRepository.findByEmailAndActive(username, true);
		System.out.println(ru.getEmail());
		List<Destination> allDestinations = findAll();
		List<DestinationDTO> resultDestinations = new ArrayList<DestinationDTO>();

		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(ru);

		allDestinations.forEach(kieSession::insert);

		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		kieSession.dispose();

		Collections.sort(allDestinations);
		return allDestinations;
	}

	public List<Destination> filterBySearchParams(SearchDTO searchDTO) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		RegisteredUser ru = registeredUserRepository.findByEmailAndActive(username, true);
		System.out.println(ru.getEmail());
		List<Destination> allDestinations = findAll();
		List<DestinationDTO> resultDestinations = new ArrayList<DestinationDTO>();

		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(ru);

		kieSession.insert(searchDTO);

		allDestinations.forEach(kieSession::insert);

		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		kieSession.dispose();

		Collections.sort(allDestinations);
		return allDestinations;
	}

}
