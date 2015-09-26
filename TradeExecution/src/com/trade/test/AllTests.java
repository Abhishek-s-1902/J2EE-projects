package com.trade.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * @AllTests: implementation of Junit test suite for combined execution of several test files
 */
@RunWith(Suite.class)
@SuiteClasses({ checkTradeDecisionTest.class, secondTestTradeDecision.class })
public class AllTests {

}
