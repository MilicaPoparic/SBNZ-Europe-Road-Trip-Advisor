package com.sbnz.unit;

import static org.junit.Assert.assertEquals;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.dto.LoginEventDTO;
import com.sbnz.event.MultipleLoginFailedEvent;
import com.sbnz.model.Category;
import com.sbnz.model.Destination;
import com.sbnz.model.RegisteredUser;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MultipleLoginFailedUnitTest {

	RegisteredUser ru;
	ArrayList<Destination> destinations;
	private static KieContainer kieContainer;
	private KieSession kieSession;

	@Before
	public void setup() {
		KieServices kieServices = KieServices.Factory.get();
		kieContainer = kieServices
				.newKieContainer(kieServices.newReleaseId("com.drools", "sbnz-drools", "0.0.1-SNAPSHOT"));

		kieSession = kieContainer.newKieSession("cepKsessionPseudo");

		// Logged in user
		Date date = new GregorianCalendar(1997, Calendar.FEBRUARY, 11).getTime();
		ru = new RegisteredUser(1L, "testuser@gmail.com", "asdf", "Test", "User", date, "employed", 44.25, 21.56, true,
				true);
		List<Category> interests = new ArrayList<Category>();

		ru.setInterests(interests);

	}

	@Test
	public void testMultipleFailedLogin() {
		SessionPseudoClock clock = kieSession.getSessionClock();

		kieSession.getAgenda().getAgendaGroup("login").setFocus();

		kieSession.insert(ru);

		kieSession.insert(new LoginEventDTO(ru.getEmail()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new LoginEventDTO(ru.getEmail()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new LoginEventDTO(ru.getEmail()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		kieSession.insert(new LoginEventDTO(ru.getEmail()));
		clock.advanceTime(5, TimeUnit.SECONDS);
		int firedRules = kieSession.fireAllRules();

		Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(MultipleLoginFailedEvent.class));
		assertEquals(1, newEvents.size());
		assertEquals(1, firedRules);
	}

}
