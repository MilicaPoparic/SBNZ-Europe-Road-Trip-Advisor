package com.sbnz.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.dto.LoginEventDTO;
import com.sbnz.dto.UserFavoriteDestinationDTO;
import com.sbnz.event.DestinationAccessEvent;
import com.sbnz.model.Category;
import com.sbnz.model.Destination;
import com.sbnz.model.Hotel;
import com.sbnz.model.LocalFood;
import com.sbnz.model.RegisteredUser;
import com.sbnz.model.Transportation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DestinationAccessUnitTest {

	private static KieContainer kieContainer;


	RegisteredUser ru;
	ArrayList<Destination> destinations;
	
	private KieSession kieSession;


	@Before
	public void setup() {
		KieServices kieServices = KieServices.Factory.get();
		kieContainer = kieServices
				.newKieContainer(kieServices.newReleaseId("com.drools", "sbnz-drools", "0.0.1-SNAPSHOT"));

		kieSession = kieContainer.newKieSession("cepKsessionPseudo");
		
		// Local food lists
		List<LocalFood> d1food = new ArrayList<LocalFood>();
		d1food.add(LocalFood.spanish);


		// Transportation lists
		List<Transportation> d1transport = new ArrayList<Transportation>();
		d1transport.add(Transportation.plane);
		d1transport.add(Transportation.bus);
		d1transport.add(Transportation.car);
		

		// Images lists
		List<String> d1images = new ArrayList<String>();
		d1images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
		

		// Hotel lists
		Hotel h1 = new Hotel(1L, "Alta Mar", 4, true, true);
		Hotel h2 = new Hotel(2L, "Mandarin Oriental Ritz", 5, true, true);
		Hotel h3 = new Hotel(3L, "Four Seasons Madrid", 5, true, false);


		List<Hotel> d1hotels = new ArrayList<Hotel>();
		d1hotels.add(h1);
		d1hotels.add(h2);
		d1hotels.add(h3);
		
		
		// Categories lists
		Category shopping = new Category(1L, "shopping", true);
		Category concerts = new Category(2L, "concerts", true);
		Category museums = new Category(3L, "museums", true);
		Category mountains = new Category(4L, "mountains", true);
		Category sea = new Category(5L, "sea", true);
		Category night_life = new Category(6L, "night life", true);
		Category history = new Category(7L, "history", true);
		Category spa = new Category(8L, "spa", true);
		Category sailing = new Category(9L, "sailing", true);
		Category adventure = new Category(10L, "adventure", true);
		Category hiking = new Category(11L, "hiking", true);
		Category luna_park = new Category(12L, "luna park", true);
		Category city = new Category(13L, "city", true);

		List<Category> d1categories = new ArrayList<Category>();
		d1categories.add(shopping);
		d1categories.add(museums);
		d1categories.add(city);


		// Destinations
		Destination d1 = new Destination(1L, "Madrid", 40.41, -3.70, d1food, d1transport, 0.0, false, true, d1images);
		d1.setCategories(d1categories);
		d1.setHotels(d1hotels);
		
		destinations = new ArrayList<Destination>();
		destinations.add(d1);


		// Logged in user
		Date date = new GregorianCalendar(1997, Calendar.FEBRUARY, 11).getTime();
		ru = new RegisteredUser(1L, "testuser@gmail.com", "asdf", "Test", "User", date, "employed", 44.25, 21.56, true,
				true);
		List<Category> interests = new ArrayList<Category>();
		interests.add(shopping);
		interests.add(museums);
		interests.add(city);
		interests.add(concerts);
		ru.setInterests(interests);
	
	}
	
	
	@Test
	public void testMultipleDestinationAccess() {
		 SessionPseudoClock clock = kieSession.getSessionClock();

		kieSession.getAgenda().getAgendaGroup("destination-access").setFocus();
	
		kieSession.insert(new UserFavoriteDestinationDTO(ru.getEmail(), destinations.get(0).getName()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new UserFavoriteDestinationDTO(ru.getEmail(), destinations.get(0).getName()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new UserFavoriteDestinationDTO(ru.getEmail(), destinations.get(0).getName()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new UserFavoriteDestinationDTO(ru.getEmail(), destinations.get(0).getName()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		int firedRules = kieSession.fireAllRules();

	    Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(DestinationAccessEvent.class));
	    assertEquals(1, newEvents.size());
		assertEquals(1, firedRules);
	}

}
