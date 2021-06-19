package com.sbnz;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sbnz.unit.DestinationAccessUnitTest;
import com.sbnz.unit.DestinationServiceUnitTests;
import com.sbnz.unit.MultipleLoginFailedUnitTest;
import com.sbnz.unit.QueriesUnitTest;


@RunWith(Suite.class)
@SuiteClasses({DestinationServiceUnitTests.class, DestinationAccessUnitTest.class, MultipleLoginFailedUnitTest.class, QueriesUnitTest.class})
public class SuiteAll {


}
