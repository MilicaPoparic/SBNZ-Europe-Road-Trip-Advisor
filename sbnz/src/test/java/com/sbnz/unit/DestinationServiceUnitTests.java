package com.sbnz.unit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.model.Category;
import com.sbnz.model.Destination;
import com.sbnz.model.Hotel;
import com.sbnz.model.LocalFood;
import com.sbnz.model.RegisteredUser;
import com.sbnz.model.Transportation;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DestinationServiceUnitTests {

	private static KieContainer kieContainer;

    private static final String default_agenda = "default";
    
    RegisteredUser ru;
    ArrayList<Destination> destinations;
    

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("com.drools", "sbnz-drools", "0.0.1-SNAPSHOT"));
                
        // Local food lists
        List<LocalFood> d1food = new ArrayList<LocalFood>(); 
        d1food.add(LocalFood.spanish);
        List<LocalFood> d2food = new ArrayList<LocalFood>(); 
        d2food.add(LocalFood.german);
        d2food.add(LocalFood.chinese);
        List<LocalFood> d3food = new ArrayList<LocalFood>(); 
        d3food.add(LocalFood.french);
        List<LocalFood> d4food = new ArrayList<LocalFood>(); 
        d4food.add(LocalFood.italian);
        List<LocalFood> d5food = new ArrayList<LocalFood>(); 
        d5food.add(LocalFood.german);
        d5food.add(LocalFood.balkan);
        
        // Transportation lists
        List<Transportation> d1transport = new ArrayList<Transportation>();
        d1transport.add(Transportation.plane);
        d1transport.add(Transportation.bus);
        d1transport.add(Transportation.car);
        List<Transportation> d2transport = new ArrayList<Transportation>();
        d2transport.add(Transportation.plane);
        d2transport.add(Transportation.ship);
        List<Transportation> d3transport = new ArrayList<Transportation>();
        d3transport.add(Transportation.plane);
        d3transport.add(Transportation.ship);
        d3transport.add(Transportation.car);
        List<Transportation> d4transport = new ArrayList<Transportation>();
        d4transport.add(Transportation.plane);
        d4transport.add(Transportation.bus);
        d4transport.add(Transportation.car);
        List<Transportation> d5transport = new ArrayList<Transportation>();
        d5transport.add(Transportation.plane);
        d5transport.add(Transportation.bus);
        d5transport.add(Transportation.train);
        d5transport.add(Transportation.car);
        
        // Images lists
        List<String> d1images= new ArrayList<String>();
        d1images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
        List<String> d2images= new ArrayList<String>();
        d2images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
        List<String> d3images= new ArrayList<String>();
        d3images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
        List<String> d4images= new ArrayList<String>();
        d4images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
        List<String> d5images= new ArrayList<String>();
        d5images.add("https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg");
        
        // Hotel lists
        Hotel h1 = new Hotel(1L, "Alta Mar", 4, true, true);
        Hotel h2 = new Hotel(2L, "Mandarin Oriental Ritz", 5, true, true);
        Hotel h3 = new Hotel(3L, "Four Seasons Madrid", 5, true, false);
        Hotel h4 = new Hotel(4L, "Britannia International", 2, true, true);
        Hotel h5 = new Hotel(5L, "Smart Hyde Park View", 3, true, false);
        Hotel h6 = new Hotel(6L, "Le Regent Monmartre", 5, true, false);
        Hotel h7 = new Hotel(7L, "Hotel Paris", 4, true, false);
        Hotel h8 = new Hotel(8L, "Shangri La", 5, true, false);
        Hotel h9 = new Hotel(9L, "Palazzo Manfredi", 4, true, true);
        Hotel h10 = new Hotel(10L, "Otivm Hotel", 2, true, false);
        Hotel h11 = new Hotel(11L, "Park Inn by Radisson", 5, true, true);
        Hotel h12 = new Hotel(12L, "Alexanderplatz", 4, true, true);
        Hotel h13 = new Hotel(13L, "Adlon Hotel", 1, true, true);
        
        List<Hotel> d1hotels = new ArrayList<Hotel>();
        d1hotels.add(h1);
        d1hotels.add(h2);
        d1hotels.add(h3);
        List<Hotel> d2hotels = new ArrayList<Hotel>();
        d2hotels.add(h4);
        d2hotels.add(h5);
        List<Hotel> d3hotels = new ArrayList<Hotel>();
        d3hotels.add(h6);
        d3hotels.add(h7);
        d3hotels.add(h8);
        List<Hotel> d4hotels = new ArrayList<Hotel>();
        d4hotels.add(h9);
        d4hotels.add(h10);
        List<Hotel> d5hotels = new ArrayList<Hotel>();
        d5hotels.add(h11);
        d5hotels.add(h12);
        d5hotels.add(h13);      
        
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
        List<Category> d2categories = new ArrayList<Category>();
        d2categories.add(shopping);
        d2categories.add(concerts);
        d2categories.add(night_life);
        List<Category> d3categories = new ArrayList<Category>();
        d3categories.add(shopping);
        d3categories.add(museums);
        d3categories.add(city);
        List<Category> d4categories = new ArrayList<Category>();
        d4categories.add(shopping);
        d4categories.add(history);
        d4categories.add(city);
        List<Category> d5categories = new ArrayList<Category>();
        d5categories.add(shopping);
        d5categories.add(museums);
        d5categories.add(luna_park);
             
        
        // Destinations
        Destination d1 = new Destination(1L, "Madrid",40.41, -3.70, d1food, d1transport, 0.0, false, true, d1images);
        d1.setCategories(d1categories);
        d1.setHotels(d1hotels);
        Destination d2 = new Destination(2L, "London",51.41, -0.12, d2food, d2transport, 0.0, false, true, d2images);
        d2.setCategories(d2categories);
        d2.setHotels(d2hotels);
        Destination d3 = new Destination(3L, "Paris",48.41, 2.70, d3food, d3transport, 0.0, false, true, d3images);
        d3.setCategories(d3categories);
        d3.setHotels(d3hotels);
        Destination d4 = new Destination(4L, "Rome",41.41, 12.70, d4food, d4transport, 0.0, false, true, d4images);
        d4.setCategories(d4categories);
        d4.setHotels(d4hotels);
        Destination d5 = new Destination(5L, "Berlin",52.52, 13.40, d5food, d5transport, 0.0, false, true, d5images);
        d5.setCategories(d5categories);
        d5.setHotels(d5hotels);
        destinations = new ArrayList<Destination>();
        destinations.add(d1);
        destinations.add(d2);
        destinations.add(d3);
        destinations.add(d4);
        destinations.add(d5);
        
        
        // Logged in user
        Date date = new GregorianCalendar(1997, Calendar.FEBRUARY, 11).getTime();
        ru = new RegisteredUser(1L, "testuser@gmail.com", "asdf", "Test", "User", date, "student", 44.25, 21.56, true, true);
        List<Category> interests = new ArrayList<Category>();
        interests.add(shopping);
        interests.add(museums);
        interests.add(city);
        ru.setInterests(interests);
        
        
	}

	@Test
	public void testAddScoreBasedOnUserProfile() {
		KieSession kieSession = kieContainer.newKieSession("test-session");
        kieSession.getAgenda().getAgendaGroup(default_agenda).setFocus();
        
        kieSession.insert(ru);
        destinations.forEach(kieSession::insert);

        kieSession.fireAllRules();
        
        // 10 points for having 2 categories for younger population
        // 15 points for having 3 users interests
        assertEquals(Double.valueOf(25.0), destinations.get(0).getScore());
        
        // 15 points for having 3 categories for younger population
        // 5 points for having 1 users interests
        assertEquals(Double.valueOf(20.0), destinations.get(1).getScore());
        
        // 15 points for having 3 categories for younger population
        // 10 points for having 2 users interests
        assertEquals(Double.valueOf(25.0), destinations.get(2).getScore());
        
        // 10 points for having 2 categories for younger population
        // 10 points for having 2 users interests
        assertEquals(Double.valueOf(20.0), destinations.get(3).getScore());
        
        // 5 points for having 1 category for younger population
        // 10 points for having 2 users interests
        assertEquals(Double.valueOf(15.0), destinations.get(4).getScore());
	}
}
