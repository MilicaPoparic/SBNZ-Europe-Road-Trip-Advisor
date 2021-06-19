package com.sbnz.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sbnz.dto.DestinationEventsDTO;
import com.sbnz.dto.EventDTO;
import com.sbnz.dto.SearchDTO;
import com.sbnz.dto.UserFavoriteDestinationDTO;
import com.sbnz.event.DestinationAccessEvent;
import com.sbnz.model.Category;
import com.sbnz.model.Destination;
import com.sbnz.model.RegisteredUser;
import com.sbnz.model.Transportation;
import com.sbnz.repository.CategoryRepository;
import com.sbnz.repository.DestinationRepository;
import com.sbnz.repository.RegisteredUserRepository;

@Service
public class DestinationService implements ServiceInterface<Destination> {

	@Autowired
	private DestinationRepository destinationRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RegisteredUserRepository registeredUserRepository;

	@Autowired
	private KieContainer kieContainer;

	@Autowired
	private KieSessionService kieSessionService;

	private final RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(DestinationService.class);

	@Override
	public List<Destination> findAll() {
		return destinationRepository.findByActive(true);
	}

	@Override
	public Destination findOne(Long id) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = currentUser.getName();
		Destination destination = destinationRepository.findByIdAndActive(id, true).orElse(null);
		UserFavoriteDestinationDTO favoriteDestination = new UserFavoriteDestinationDTO(username,
				destination.getName());
		KieSession kieSession = kieSessionService.getCepSession();
		kieSession.insert(favoriteDestination);
		kieSession.getAgenda().getAgendaGroup("destination-access").setFocus();
		System.out.println("Favorite destination rules fired!");
		kieSession.fireAllRules();
		return destination;
	}

	@Override
	public Destination create(Destination entity) throws Exception {
		// make new Destination instance
		Destination c = new Destination();
		c.setName(entity.getName());
		c.setLocationLat(entity.getLocationLat());
		c.setLocationLon(entity.getLocationLon());
		c.setLocalFood(entity.getLocalFood());
		c.setHotels(entity.getHotels());
		c.setScore(entity.getScore());
		c.setTransportation(entity.getTransportation());
		c.setTrending(false);
		c.setActive(true);
		List<Category> setterList = new ArrayList<Category>();
		List<Category> categories = categoryRepository.findAll();
		for (Category category : entity.getCategories()) {
			for (Category category2 : categories) {
				if (category.getName().equals(category2.getName())) {
					setterList.add(category2);
				}
			}
		}
		c.setCategories(setterList);
		c.setImages(entity.getImages());
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
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(ru);
		allDestinations.forEach(kieSession::insert);
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		KieSession cepSession = kieSessionService.getCepSession();
		cepSession.getAgenda().getAgendaGroup("destination-access").setFocus();
		Collection<?> destinationAccessEvents = cepSession
				.getObjects(new ClassObjectFilter(DestinationAccessEvent.class));
		Iterator<?> iterator = destinationAccessEvents.iterator();
		while (iterator.hasNext()) {
			DestinationAccessEvent ev = (DestinationAccessEvent) iterator.next();
			for (Destination dest : allDestinations) {
				if (ev.getDestination().equals(dest.getName()) && ev.getUsername().equals(username)) {
					dest.setScore(dest.getScore() + 20);
				}
			}
		}

		Collections.sort(allDestinations);

		return allDestinations;
	}

	public ArrayList<Destination> reportForChildrenDiscount() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults destinationsForFamilies = kieSession.getQueryResults("Get destinations with children discount",
				true);
		for (QueryResultsRow queryResult : destinationsForFamilies) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForTrending() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);

		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults trendingDestinations = kieSession.getQueryResults("Get trending destination", true);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		for (QueryResultsRow queryResult : trendingDestinations) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForCategory(String category) {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults destinationsWithCategory = kieSession.getQueryResults("Get destinations with selected category",
				category);
		for (QueryResultsRow queryResult : destinationsWithCategory) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForTransportation(String transport) {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults transportationList = kieSession.getQueryResults(
				"Get destination with selected transportation type", Transportation.valueOf(transport));
		for (QueryResultsRow queryResult : transportationList) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForMediterranean() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults mediterranean = kieSession.getQueryResults("Get mediterranean destinations");
		for (QueryResultsRow queryResult : mediterranean) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForNorthEurope() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults northEurope = kieSession.getQueryResults("Get north europe destinations");
		for (QueryResultsRow queryResult : northEurope) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForEastEurope() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults eastEurope = kieSession.getQueryResults("Get eastern europe destinations");
		for (QueryResultsRow queryResult : eastEurope) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public ArrayList<Destination> reportForBalkan() {
		List<Destination> allDestinations = findAll();
		KieSession kieSession = kieContainer.newKieSession("test-session");
		allDestinations.forEach(kieSession::insert);
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		QueryResults balkan = kieSession.getQueryResults("Get balkan destinations");
		for (QueryResultsRow queryResult : balkan) {
			Destination d = (Destination) queryResult.get("$destinations");
			destinations.add(d);
		}
		kieSession.dispose();
		return destinations;
	}

	public List<Destination> filterBySearchParams(SearchDTO searchDTO) throws JSONException, ParseException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		RegisteredUser ru = registeredUserRepository.findByEmailAndActive(username, true);
		List<Destination> allDestinations = findAll();

		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(ru);

		allDestinations.forEach(kieSession::insert);

		kieSession.getAgenda().getAgendaGroup("default").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());

		if (searchDTO.numberOfPeople == null)
			searchDTO.numberOfPeople = 1;

		if (searchDTO.transportation != null) {
			kieSession.insert(searchDTO);
			kieSession.getAgenda().getAgendaGroup("transportation").setFocus();
			logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		} else {
			searchDTO.setTransportation(Transportation.car);
			kieSession.insert(searchDTO);
		}

		kieSession.getAgenda().getAgendaGroup("budget").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());

		if (searchDTO.localFood != null) {
			kieSession.getAgenda().getAgendaGroup("food").setFocus();
			logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		}
		if (searchDTO.accommodation != null) {
			kieSession.getAgenda().getAgendaGroup("accommodation").setFocus();
			logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		}
		if (searchDTO.children != null) {
			kieSession.getAgenda().getAgendaGroup("children").setFocus();
			logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		}
		if (searchDTO.maxDistance != 0) {
			kieSession.getAgenda().getAgendaGroup("distance").setFocus();
			logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());
		}

		// get upcoming events
		List<DestinationEventsDTO> eventsList = new ArrayList<DestinationEventsDTO>();
		for (Destination d : allDestinations) {
			DestinationEventsDTO de = new DestinationEventsDTO();
			de.setDestination(d);
			de.setEvents(getEvents(d.getLocationLat(), d.getLocationLon()));
			eventsList.add(de);
		}
		eventsList.forEach(kieSession::insert);

		kieSession.getAgenda().getAgendaGroup("events").setFocus();
		logger.info("Filtering destinations - fired: " + kieSession.fireAllRules());

		kieSession.dispose();

		Collections.sort(allDestinations);

		return allDestinations;
	}

	public ArrayList<EventDTO> getEvents(Double lat, Double lon) throws JSONException, ParseException {

		String url = "HTTPS://api.predicthq.com/v1/events/?location_around.decay=0.5&location_around.offset=100km&location_around.origin="
				+ lat + "," + lon + "&location_around.scale=10km&page=0&size=100";

		final HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer qkVJ57ag0XPVM-JlGnaytrlBtRFnzD_jrDPCqEDO");
		headers.set("Accept", "application/json");

		// Create a new HttpEntity
		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		// Execute the method writing your HttpEntity to the request
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
		// System.out.println(response.getBody());

		JSONObject json = new JSONObject(response.getBody());
		@SuppressWarnings("unchecked")
		ArrayList<Object> resultsList = (ArrayList) json.get("results");
		Gson gson = new Gson();

		ArrayList<EventDTO> events = new ArrayList<EventDTO>();

		for (Object o : resultsList) {
			String objectString = gson.toJson(o);
			JSONObject objectJSON = new JSONObject(objectString);

			String title = objectJSON.getString("title");
			String category = objectJSON.getString("category");
			Date start = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(objectJSON.getString("start"));
			Date end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(objectJSON.getString("end"));
			String location = objectJSON.getString("location");
			Double latitude = Double.parseDouble(location.substring(1, location.length() - 1).split(",")[1]);
			Double longitude = Double.parseDouble(location.substring(1, location.length() - 1).split(",")[0]);

			EventDTO event = new EventDTO(title, category, start, end, latitude, longitude);
			events.add(event);
		}
		return events;
	}

}
