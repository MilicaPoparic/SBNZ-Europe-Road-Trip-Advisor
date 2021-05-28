package com.sbnz.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.apache.poi.ss.formula.functions.Rate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbnz.repository.DestinationRepository;
import com.sbnz.repository.RegisteredUserRepository;
import com.sbnz.service.DestinationService;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class DestinationServiceUnitTests {
	@Autowired
	private DestinationService destinationService;

	@MockBean
	private DestinationRepository destinationRepository;

	@MockBean
	private RegisteredUserRepository registeredUserRepository;



	@Before
	public void setup() {

	}

	@Test
	public void testFindAll() {

	}
}
