package com.sbnz;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.sbnz.unit.DestinationServiceUnitTests;


@RunWith(Suite.class)
@SuiteClasses({DestinationServiceUnitTests.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}
